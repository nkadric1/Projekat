package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;

import java.sql.*;
import java.util.List;

public class ProjectDAOSQLImpl implements ProjectDao{
    private Connection con;
    public ProjectDAOSQLImpl(){
        try{
            this.con= DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582729", "sql7582729", "7F1FfHWIiY");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    public Project getById(int ID) {
        String q="SELECT * FROM Project WHERE ID_pro = ?";
        try{
            PreparedStatement s=this.con.prepareStatement(q);
            s.setInt(1,ID);
            ResultSet r=s.executeQuery();
            if(r.next()){
                Project p=new Project();
                p.setID(r.getInt("ID_pro"));
               p.setProject_name(r.getString(2));
                r.close();
                return p;
            } else{ return null;}

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Project add(Project x) {
        String insert = "INSERT INTO Project(Project_name) VALUES(?)";
        try{
            PreparedStatement tmp= this.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            tmp.setString(1, x.getProject_name());
            tmp.executeUpdate();

            ResultSet r= tmp.getGeneratedKeys();
            r.next();
            x.setID(r.getInt(1));
            return x;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Project update(Project x) {
        String insert = "UPDATE Project SET Project_name = ? WHERE ID_pro = ?";
        try{
            PreparedStatement tmp = this.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            tmp.setObject(1, x.getProject_name());

            tmp.executeUpdate();
            return x;
        }catch (SQLException e){
            e.printStackTrace();}
        return null;
    }

    @Override
    public void delete(int ID) {
        String insert = "DELETE FROM Project WHERE ID_pro = ?";
        try{
            PreparedStatement stmt = this.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, ID);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
//dodati ovu metodu
    @Override
    public List<Project> getAll() {
        return null;
    }


}
