package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

import java.io.FileReader;
import java.sql.*;
import java.util.*;

/** @author Kadrić Nerma
 * class where is implementation of methods we will use to manipulate the departments
 */
public class DepartmentDAOSQLImpl extends AbstractDao<Department> implements  DepartmentDao{




    public DepartmentDAOSQLImpl() {

       super("Departments");
    }




    /**
     * this method return Department whose ID is passed
     * @param id
     * @return department
     */
    @Override
    public Department ReturnDepartmentForId(int id) throws EmployeeException {
        String q = "SELECT * FROM Department WHERE id = ?";
        try {
            PreparedStatement tmp = getConnection().prepareStatement(q);
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
            throw new EmployeeException(e.getMessage(),e);
        }

        return null;
    }

    @Override
    public Department rowtoobject(ResultSet r)  throws EmployeeException {
        try{
            Department d=new Department();
            d.setId(r.getInt("id"));
            d.setHourlypay(r.getInt("Hourly_pay"));
            d.setDepname(r.getString("Department_name"));
            return d;
        }catch(SQLException e){
           throw new EmployeeException(e.getMessage(),e);
        }

    }

    @Override
    public Map<String, Object> objecttorow(Department object) {
       Map<String , Object> i=new TreeMap<String,Object>();
       i.put("id",object.getId());
       i.put("Hourly_pay",object.getHourlypay());
       i.put("Department_name", object.getDepname());
       return i;
    }
}
