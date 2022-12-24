package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
public interface DepartmentDao extends Dao<Department> {


   public Department ReturnDepartmentForId(int id) throws EmployeeException;

}
