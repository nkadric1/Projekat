package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/** @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the projects
 */
public class ProjectDAOSQLImpl implements ProjectDao{
    private Connection con;
    public ProjectDAOSQLImpl(){
        try {
            FileReader reader=new FileReader("");
            Properties p=new Properties();
            p.load(reader);
            String url=p.getProperty("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582729");
            String username=p.getProperty("sql7582729");
            String password="7F1FfHWIiY";
            this.con = DriverManager.getConnection(url,username , password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     *
     * @param ID
     * @return department of Id which we passed as parameter
     */
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
/**
        * this method add new project into the base
     * @param x
     * @return new project
     */
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
    /**
     * this method updates the database with the parameter we passed to it
     * @param x
     * @return updated project
     */
    @Override
    public Project update(Project x) {
        String insert = "UPDATE Project SET Project_name = ? WHERE ID_pro = ?";
        try{
            PreparedStatement tmp = this.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            tmp.setInt(1, x.getID());
            tmp.setString(2, x.getProject_name());

            tmp.executeUpdate();
            return x;
        }catch (SQLException e){
            e.printStackTrace();}
        return null;
    }
    /**
     * this method deletes the project whose id is passed
     * @param ID
     */

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
    /**
     *
     * @return list of departments
     */
    @Override
    public List<Project> getAll() {
        List<Project> p = new ArrayList<>();
        try{
            PreparedStatement tmp= this.con.prepareStatement("SELECT * FROM Project");
            ResultSet r = tmp.executeQuery();
            while (r.next()){
           Project a=new Project();
                a.setID(r.getInt(1));
                a.setProject_name(r.getString(2));

                p.add(a);
            }
            r.close();
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());}
        return p;
    }


}
