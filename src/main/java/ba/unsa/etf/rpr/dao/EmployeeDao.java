package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;

import java.util.List;

/**
 * @Kadrić Nerma
 * Interface for Employee domain bean as Dao
 * three methods that helps user to get informations about employees
 * to search employees by department in which they work
 * to search employees by project on which they work
 * to get employees by date they started to work

 */
public interface EmployeeDao extends Dao<Employee> {

  public  List<Employee> searchByDepartment(int Id);
 public   List<Employee> searchByProject(int id);
  public  List<Employee> getByHireDate();

  public Employee getfromID(int id);
  public int returnSalary(int sal);
}
