package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<tt extends Idable> implements Dao<tt> {
    private Connection con;
    private String name;

    public AbstractDao(String n) {
        try {
            this.name = n;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("database.properties").openStream());
            String url = p.getProperty("url");
            String username = p.getProperty("username");
            String password = p.getProperty("password");
            this.con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Problem with DB");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return this.con;
    }
    public void setConnection(Connection c){ this.con=c;}

    public abstract tt rowtoobject(ResultSet r) throws EmployeeException;

    public abstract Map<String, Object> objecttorow(tt object);

    public tt getById(int id) throws EmployeeException{

        return executeQUq("SELECT * FROM" + this.name + " WHERE id = ?",new Object[]{id});

    }
    public List<tt> getAll() throws EmployeeException{
        return executeQ("SELECT * FROM "+ name, null);

    }
    public void delete(int id) throws EmployeeException{
        String sq="DELETE FROM "+name+" WHERE id = ?";
        try{
            PreparedStatement s=getConnection().prepareStatement(sq,Statement.RETURN_GENERATED_KEYS);
            s.setObject(1,id);
            s.executeUpdate();
        }catch (SQLException e){
           throw new EmployeeException(e.getMessage(),e);
        }
    }
    public tt update(tt i) throws EmployeeException{
        Map<String, Object> r=objecttorow(i);
        String ucol=prepareUpdateParts(r);
        StringBuilder build=new StringBuilder();
        build.append("UPDATE ").append(name).append(" SET ").append(ucol).append(" WHERE id = ?");
        try{
            PreparedStatement s=getConnection().prepareStatement(build.toString());
            int c=1;
            for (Map.Entry<String,Object> e: r.entrySet()){
                if(e.getKey().equals("id")) continue;
                s.setObject(c,e.getValue()); c++;
            }
            s.setObject(c+1,i.getId());
            s.executeUpdate();
            return i;
        }catch(SQLException e){
            throw new EmployeeException(e.getMessage(),e);}

    }
    public tt add(tt i) throws EmployeeException{
        Map<String,Object> r=objecttorow(i);
        Map.Entry<String,String> col=prepareInsertParts(r);
        StringBuilder build=new StringBuilder();
        build.append("INSERT INTO ").append(name);
        build.append(" (").append(col.getKey()).append(") ");
        build.append("VALUES (").append(col.getValue()).append(")");
        try{
            PreparedStatement s=getConnection().prepareStatement(build.toString(),Statement.RETURN_GENERATED_KEYS);
            int c=1;
            for (Map.Entry<String,Object> e: r.entrySet()){
                if(e.getKey().equals("id")) continue;
                s.setObject(c,e.getValue()); c++;
            }
            s.executeUpdate();
            ResultSet rset=s.getGeneratedKeys();
            rset.next();
            i.setId(rset.getInt(1));
            return i;
        }catch(SQLException e){
           throw new EmployeeException(e.getMessage(),e);
        }

    }


    private Map.Entry<String, String> prepareInsertParts(Map<String,Object> r){
        StringBuilder col=new StringBuilder();
        StringBuilder que=new StringBuilder();
        int c=0;
        for(Map.Entry<String,Object> e: r.entrySet()){
            c++;
            if(e.getKey().equals("id")) continue;
            col.append(e.getKey());
            que.append("?");
            if(r.size()!=c){
                col.append(",");
                que.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(col.toString(),que.toString());
    }
    private String prepareUpdateParts(Map<String,Object> r){
        StringBuilder col=new StringBuilder();
        int c=0;
        for(Map.Entry<String,Object> e: r.entrySet()){
            c++;
            if(e.getKey().equals("id")) continue;
            col.append(e.getKey()).append("= ?");
            if(r.size()!=c){
                col.append(",");
            }
        }
       return  col.toString();
    }
    public List<tt> executeQ(String q, Object[] p) throws EmployeeException{
        try{
            PreparedStatement tmp=getConnection().prepareStatement(q);
            if(p!=null){
                for(int i=1;i<=p.length;i++) tmp.setObject(i,p[i-1]);
            }
            ResultSet r=tmp.executeQuery();
            ArrayList<tt> rlist=new ArrayList<>();
            while(r.next()) rlist.add(rowtoobject(r));
            return rlist;
        }catch(SQLException e){
            throw new EmployeeException(e.getMessage(),e);
        }
    }
    public tt executeQUq(String q, Object[] p) throws EmployeeException{
        List<tt> r=executeQ(q,p);
        if(r!=null && r.size()==1){
            return r.get(0);
        }
        else{
            throw new EmployeeException("Not found!");
        }
    }
}