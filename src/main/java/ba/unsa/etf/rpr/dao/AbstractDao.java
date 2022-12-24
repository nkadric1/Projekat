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
        }
    }

    public Connection getConnection() {
        return this.con;
    }

    public abstract tt rowtoobject(ResultSet r) throws EmployeeException;

    public abstract Map<String, Object> objecttorow(tt object);

    public tt getById(int id) throws EmployeeException{
        String q = "SELECT * FROM" + this.name + " id = ?";
        try {
            PreparedStatement s = this.con.prepareStatement(q);
            s.setInt(1, id);
            ResultSet r = s.executeQuery();
            if (r.next()) {
                tt res=rowtoobject(r);
                r.close();
              return res;
            } else { //dodati svoj exc
                return null;
            }


        } catch (SQLException e) {
          throw new EmployeeException(e.getMessage(),e);
        }

    }
    public List<tt> getAll() throws EmployeeException{
        String q="SELECT * FROM "+ name;
        List<tt> res=new ArrayList<tt>();
        try{
            PreparedStatement s=getConnection().prepareStatement(q);
            ResultSet r=s.executeQuery();
            while(r.next()){
                tt obj=rowtoobject(r);
                res.add(obj);
            }
            r.close();
            return  res;
        }catch(SQLException e) {
          throw new EmployeeException(e.getMessage(),e);}

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
}