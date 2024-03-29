package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

import java.sql.*;
import java.util.*;


/**
 * @author Kadric Nerma
 * MySQL implementation of the DAO
 */
public class EmployeeDAOSQLImpl extends AbstractDao<Employee> implements EmployeeDao {

    private static EmployeeDAOSQLImpl instance = null;

    public static EmployeeDAOSQLImpl getInstance() {
        if (instance == null) instance = new EmployeeDAOSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) instance = null;
    }

    public EmployeeDAOSQLImpl() {
        super("Employee");
    }


    /**
     * @param Id - PK of department
     * @return - list of employees of that department
     */
    @Override
    public List<Employee> searchByDepartment(int Id) throws EmployeeException {
        return executeQ("SELECT * FROM Employee WHERE department_id = ?", new Object[]{Id});
    }

    /**
     * @param id - PK of project
     * @return - number of employees that works on that project
     */
    @Override
    public int returnNumberofEmployees(int id) throws EmployeeException {
        String sq = "SELECT COUNT(*) FROM Employee WHERE project_id = ?";
        try {
            PreparedStatement s = getConnection().prepareStatement(sq, Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, id);
            ResultSet r = s.executeQuery();
            int count = 0;
            while (r.next()) {
                count++;
            }
            int all = getInstance().getAll().size();
            return count / all;
        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage(), e);
        }
    }

    /**
     * @param id- PK of project
     * @return - list of employees that works on project
     */
    @Override
    public List<Employee> searchByProject(int id) throws EmployeeException {
        return executeQ("SELECT * FROM Employee WHERE project_id = ?", new Object[]{id});
    }


    /**
     * @return - list of employees ordered by hire date
     */
    @Override
    public List<Employee> getByHireDate() throws EmployeeException {
        return executeQ("SELECT * FROM Employee ORDER BY Hire_date", null);
    }

    /**
     * @param id - PK of employee
     * @return - employee whose id is passed as parameter
     */
    @Override
    public Employee getfromID(int id) throws EmployeeException {
        return executeQUq("SELECT * FROM Employee WHERE id = ?", new Object[]{id});
    }


    @Override
    public Employee rowtoobject(ResultSet r) throws EmployeeException {
        try {
            Employee emp = new Employee();
            emp.setId(r.getInt("id"));
            emp.setFirst_name(r.getString("First_name"));
            emp.setLast_name(r.getString("Last_name"));
            emp.setAddress(r.getString("Address"));
            emp.setHire_date(r.getDate("Hire_date").toLocalDate());
            emp.setDepartment(DaoFactory.departmentDao().getById(r.getInt("department_id")));
            emp.setEdu(r.getString("Education"));
            emp.setProject(DaoFactory.projectDao().getById(r.getInt("project_id")));
            emp.setPayoff(r.getInt("payoff"));
            emp.setDepartment(DaoFactory.departmentDao().getById(r.getInt("department_id")));

            return emp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> objecttorow(Employee object) {
        Map<String, Object> r = new TreeMap<String, Object>();
        r.put("id", object.getId());
        r.put("First_name", object.getFirst_name());
        r.put("Last_name", object.getLast_name());
        r.put("Address", object.getAddress());
        r.put("Hire_date", object.getHire_date());
        r.put("department_id", object.getDepartment().getId());
        r.put("Education", object.getEdu());
        r.put("project_id", object.getProject().getId());
        r.put("payoff", object.getPayoff());

        return r;

    }
}
