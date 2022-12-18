package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Department;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/** @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the departments
 */
public class DepartmentDAOSQLImpl implements  DepartmentDao{
    private Connection con;



    public DepartmentDAOSQLImpl() {

        try {
            FileReader reader=new FileReader("src/main/resources/database.properties");
            Properties p=new Properties();
            p.load(reader);
            String url=p.getProperty("url");
            String username=p.getProperty("username");
            String password=p.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(url,username , password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * method that works with id
     * @return maxId+1 for a new row in which we can put a new object
     */
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement tmp = this.con.prepareStatement("SELECT MAX(ID_dep)+1 FROM Department");
            ResultSet r= tmp.executeQuery();
            if(r.next()) {
                id = r.getInt(1);
                r.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return id;
    }

    /**
     *
     * @param ID
     * @return department of Id which we passed as parameter
     */
    @Override
    public Department getById(int ID) {
        String q="SELECT * FROM Department WHERE ID_dep = ?";
        try{
            PreparedStatement s=this.con.prepareStatement(q);
            s.setInt(1,ID);
            ResultSet r=s.executeQuery();
            if(r.next()){
               Department d=new Department();
                d.setId(r.getInt(1));
                d.setHourlypay(r.getInt(2));
                d.setDepname(r.getString(3));
                r.close();
                return d;
            } else
            { return null;}

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this method add new department into the base
     * @param x
     * @return new department
     */
    @Override
    public Department add(Department x) {
        int id = getMaxId();
        try {
            PreparedStatement tmp= this.con.prepareStatement("INSERT INTO Department VALUES (id, x.getHourlypay(), x.getDepname())");
            tmp.executeUpdate();
            x.setId(id);
            return x;
        } catch (SQLException e) {
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * this method updates the database with the parameter we passed to it
     * @param x
     * @return updated department
     */
    @Override
    public Department update(Department x) {
        try{
            PreparedStatement tmp = this.con.prepareStatement("UPDATE Department SET Hourly_pay=?, Department_name=? WHERE ID_dep=?");
            tmp.setInt(1, x.getId());
            tmp.setInt(2, x.getHourlypay());
                tmp.setString(3, x.getDepname());
            tmp.executeUpdate();
            return x;
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * this method deletes the department whose id is passed
     * @param ID
     */
    @Override
    public void delete(int ID) {
        try{
            PreparedStatement tmp = this.con.prepareStatement("DELETE FROM Department WHERE ID_dep = ?");
            tmp.setInt(1, ID);
            tmp.executeUpdate();
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @return list of departments
     */
    @Override
    public List<Department> getAll() {
        List<Department> d = new ArrayList<>();
        try{
            PreparedStatement tmp= this.con.prepareStatement("SELECT * FROM Department");
            ResultSet r = tmp.executeQuery();
            while (r.next()){
                Department a=new Department();
                a.setId(r.getInt(1));
                a.setDepname(r.getString(3));
                a.setHourlypay(r.getInt(2));
                d.add(a);
            }
            r.close();
        }catch (SQLException e){
            System.out.println("Problem with DB");
            System.out.println(e.getMessage());}
        return d;
    }

    /**
     * this method return Department whose ID is passed
     * @param id
     * @return department
     */
    @Override
    public Department ReturnDepartmentForId(int id) {
        String q = "SELECT * FROM Department WHERE ID_dep = ?";
        try {
            PreparedStatement tmp = this.con.prepareStatement(q);
            tmp.setInt(1, id);
            ResultSet r = tmp.executeQuery();
            if (r.next()) {

                Department c = new Department();
                c.setId(r.getInt(1));
                c.setDepname(r.getString(3));
                c.setHourlypay(r.getInt(2));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
