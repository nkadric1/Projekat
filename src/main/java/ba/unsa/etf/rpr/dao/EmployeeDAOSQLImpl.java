package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;
import sun.reflect.generics.tree.Tree;

import java.io.FileReader;

import java.sql.*;
import java.util.*;

/** @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the employees
 */
public class EmployeeDAOSQLImpl extends AbstractDao<Employee> implements EmployeeDao {

    public EmployeeDAOSQLImpl(){
        super("Employee");
    }


    /**
     * this method returns list of employees that works in department that is passed as parameter
     * @param dep
     * @return list of employees
     */
    @Override
    public List<Employee> searchByDepartment(Department dep) {
        String q="SELECT * FROM Employee WHERE department_id= ?";
        try{
            PreparedStatement s=getConnection().prepareStatement(q);
            s.setInt(1,dep.getId());
            ResultSet r=s.executeQuery();
            ArrayList<Employee> list=new ArrayList<>();
            while(r.next()){
                Employee e=new Employee();
                e.setId(r.getInt(1));
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
            PreparedStatement s=getConnection().prepareStatement(q);
            s.setInt(1, p.getId());
            ResultSet r=s.executeQuery();
            ArrayList<Employee> list=new ArrayList<>();
            while(r.next()){
                Employee e=new Employee();
                e.setId(r.getInt(1));
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
        PreparedStatement tmp=getConnection().prepareStatement(q);
        ResultSet r=tmp.executeQuery();
        ArrayList<Employee> DateList = new ArrayList<>();
        if(r.next()){
            Employee e=new Employee();
            e.setId(r.getInt(1));
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

    @Override
    public Employee rowtoobject(ResultSet r) {
        try{
            Employee emp=new Employee();
            emp.setId(r.getInt("id"));
            emp.setFirst_name(r.getString("First_name"));
            emp.setLast_name(r.getString("Last_name"));
            emp.setAddress(r.getString("Address"));
            emp.setHire_date(r.getDate("Hire_date"));
            emp.setDepartment_id(r.getInt("department_id"));
            emp.setEdu(r.getString("Education"));
            emp.setProject_id(r.getInt("project_id"));
            emp.setAtt_id(r.getInt("att_id"));
            emp.setPayoff(r.getInt("payoff"));
            return emp;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> objecttorow(Employee object) {
        Map<String, Object> r=new TreeMap<String,Object>();
        r.put("id", object.getId());
        r.put("First_name", object.getFirst_name());
        r.put("Last_name", object.getLast_name());
        r.put("Address", object.getAddress());
        r.put("Hire_date", object.getHire_date());
        r.put("department_id",object.getDepartment_id());
        r.put("Education", object.getEdu());
        r.put("project_id", object.getProject_id());
        r.put("att_id", object.getAtt_id());
        r.put("payoff", object.getPayoff());
        return r;

    }
}
