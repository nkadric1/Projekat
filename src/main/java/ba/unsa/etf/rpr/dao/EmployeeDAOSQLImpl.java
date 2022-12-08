package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOSQLImpl implements EmployeeDao {
    private Connection con;
    public EmployeeDAOSQLImpl(){
        try{
            this.con=DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582729", "sql7582729", "7F1FfHWIiY");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Employee getById(int ID) {
        String q="SELECT * FROM employee WHERE ID = ?";
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

    @Override
    public Employee add(Employee x) {
        return null;
    }

    @Override
    public Employee update(Employee x) {
        return null;
    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public List<Employee> searchByDepartment(Departments dep) {
        String q="SELECT * FROM employee WHERE dep= ?";
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
                e.setHire_date(r.getDate("Hire_date"));
                e.setDepartment_id(dep.getID());
                e.setEdu(r.getString(7));
                e.setPayoff(r.getInt("payoff"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> searchByProject(Project p) {
        String q="SELECT * FROM employee WHERE p = ?";
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
                e.setHire_date(r.getDate("Hire_date"));
                e.setProject_id(p.getID());
                e.setEdu(r.getString(7));
                e.setPayoff(r.getInt("payoff"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> searchByEducation(String ed) {
        return null;
    }
}
