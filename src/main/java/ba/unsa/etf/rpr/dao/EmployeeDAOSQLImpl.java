package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Properties;

/** @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the employees
 */
public class EmployeeDAOSQLImpl implements EmployeeDao {
    private Connection con;
    public EmployeeDAOSQLImpl(){
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
     * @return employee of Id which we passed as parameter
     */
    @Override
    public Employee getById(int ID) {
        String q="SELECT * FROM Employee WHERE ID_emp = ?";
        try{
         PreparedStatement s=this.con.prepareStatement(q);
         s.setInt(1,ID);
         ResultSet r=s.executeQuery();
         if(r.next()){
             Employee emp=new Employee();
             emp.setID(r.getInt("ID_emp"));
             emp.setFirst_name(r.getString("First_name"));
             emp.setLast_name(r.getString("Last_name"));
             emp.setAddress(r.getString("Address"));
             emp.setHire_date(r.getDate("Hire_date"));
             emp.setDepartment_id(r.getInt("department_id"));
             emp.setEdu(r.getString("Education"));
             emp.setProject_id(r.getInt("project_id"));
             emp.setAtt_id(r.getInt("att_id"));
             emp.setPayoff(r.getInt("payoff"));
             r.close();
             return emp;
         } else{ return null;}

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this method add new employee into the base
     * @param x
     * @return added employee
     */
    @Override
    public Employee add(Employee x) {
        String insert = "INSERT INTO Employee(First_name,Last_name,Address,Hire_date,Education) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement tmp= this.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            tmp.setString(1, x.getFirst_name());
           tmp.setString(2,x.getLast_name());
           tmp.setString(3,x.getAddress());
           tmp.setDate(4,x.getHire_date());
           tmp.setString(5,x.getEdu());
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
     *  this method updates the database with the parameter we passed to it
     * @param x
     * @return updated employee
     */
    @Override
    public Employee update(Employee x) {

        String insert = "UPDATE Employee SET First_name = ?, Last_name=?, Address= ? ,Hire_date=?, Education=?, payoff=? WHERE ID_emp = ?";
        try{
            PreparedStatement tmp = this.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            tmp.setObject(1, x.getFirst_name());
            tmp.setObject(2, x.getLast_name());
            tmp.setObject(3, x.getAddress());
            tmp.setObject(4, x.getHire_date());
            tmp.setObject(5, x.getEdu());
            tmp.setObject(6, x.getPayoff());

            tmp.executeUpdate();
            return x;
        }catch (SQLException e){
            e.printStackTrace();}
            return null;

    }

    /**
     * deletes employee whose id is passed
     * @param ID
     */
    @Override
    public void delete(int ID) {
        String insert = "DELETE FROM Employee WHERE ID_emp = ?";
        try{
            PreparedStatement stmt = this.con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, ID);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * this method returns list of all employees which are registered in the database
     * @return lis of employees
     */
    @Override
    public List<Employee> getAll() {
        List<Employee> e= new ArrayList<>();
        try{
            PreparedStatement tmp= this.con.prepareStatement("SELECT * FROM Employee");
            ResultSet r = tmp.executeQuery();
            while (r.next()){
               Employee a=new Employee();
                a.setID(r.getInt(1));
                a.setFirst_name(r.getString(2));
                a.setLast_name(r.getString(3));
                a.setAddress(r.getString(4));
                a.setHire_date(r.getDate(5));
                a.setEdu(r.getString(7));
                a.setPayoff(r.getInt(10));
                e.add(a);
            }
            r.close();
        }catch (SQLException exception){
            System.out.println("Problem with DB");
            System.out.println(exception.getMessage());}
        return e;
    }

    /**
     * this method returns list of employees that works in department that is passed as parameter
     * @param dep
     * @return list of employees
     */
    @Override
    public List<Employee> searchByDepartment(Departments dep) {
        String q="SELECT * FROM Employee WHERE department_id= ?";
        try{
            PreparedStatement s=this.con.prepareStatement(q);
            s.setInt(1,dep.getID());
            ResultSet r=s.executeQuery();
            ArrayList<Employee> list=new ArrayList<>();
            while(r.next()){
                Employee e=new Employee();
                e.setID(r.getInt(1));
                e.setFirst_name(r.getString(2));
                e.setLast_name(r.getString(3));
                e.setAddress(r.getString(4));
                e.setHire_date(r.getDate(5));
                e.setEdu(r.getString(7));
                e.setPayoff(r.getInt("payoff"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this method returns list of employees that works on project that is passed as parameter
     * @param p
     * @return list of employees
     */
    @Override
    public List<Employee> searchByProject(Project p) {
        String q="SELECT * FROM Employee WHERE project_id = ?";
        try{
            PreparedStatement s=this.con.prepareStatement(q);
            s.setInt(1, p.getID());
            ResultSet r=s.executeQuery();
            ArrayList<Employee> list=new ArrayList<>();
            while(r.next()){
                Employee e=new Employee();
                e.setID(r.getInt(1));
                e.setFirst_name(r.getString(2));
                e.setLast_name(r.getString(3));
                e.setAddress(r.getString(4));
                e.setHire_date(r.getDate(5));
                e.setEdu(r.getString(7));
                e.setPayoff(r.getInt("payoff"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * this method returns list of employees ordered by hire dates
     * @return list of employees
     */
    @Override
    public List<Employee> getByHireDate() {
    String q="SELECT * FROM Employee ORDER BY Hire_date";
    try{
        PreparedStatement tmp=this.con.prepareStatement(q);
        ResultSet r=tmp.executeQuery();
        ArrayList<Employee> DateList = new ArrayList<>();
        if(r.next()){
            Employee e=new Employee();
            e.setID(r.getInt(1));
            e.setFirst_name(r.getString(2));
            e.setLast_name(r.getString(3));
            e.setAddress(r.getString(4));
            e.setHire_date(r.getDate(5));
            e.setEdu(r.getString(7));
            e.setPayoff(r.getInt("payoff"));
            DateList.add(e);
        }
        return DateList;
    }
    catch(SQLException e){
        e.printStackTrace();
        }
        return null;
    }
}
