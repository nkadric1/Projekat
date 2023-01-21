package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

import java.sql.*;
import java.util.*;


/**
 * @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the departments
 */
public class DepartmentsDAOSQLImpl extends AbstractDao<Departments> implements DepartmentsDao {
    private static DepartmentsDAOSQLImpl instance = null;

    public DepartmentsDAOSQLImpl() {
        super("Departments");
    }

    /**
     * this method return Departments whose ID is passed
     *
     * @param id
     * @return department
     */
    @Override
    public Departments ReturnDepartmentForId(int id) throws EmployeeException {
        return executeQUq("SELECT * FROM Departments WHERE id = ?", new Object[]{id});
    }

    public static DepartmentsDAOSQLImpl getInstance() {
        if (instance == null) instance = new DepartmentsDAOSQLImpl();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null) instance = null;
    }

    @Override
    public Departments rowtoobject(ResultSet r) throws EmployeeException {
        try {
            Departments d = new Departments();
            d.setId(r.getInt("id"));
            d.setHourlypay(r.getInt("Hourly_pay"));
            d.setDepname(r.getString("Department_name"));
            return d;
        } catch (SQLException e) {
            throw new EmployeeException(e.getMessage(), e);
        }

    }

    @Override
    public Map<String, Object> objecttorow(Departments object) {
        Map<String, Object> i = new TreeMap<String, Object>();
        i.put("id", object.getId());
        i.put("Hourly_pay", object.getHourlypay());
        i.put("Department_name", object.getDepname());
        return i;
    }
}
