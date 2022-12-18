package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Department;

public interface DepartmentDao extends Dao<Department> {


   public Department ReturnDepartmentForId(int id);
}
