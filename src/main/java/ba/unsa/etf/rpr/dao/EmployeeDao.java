package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Project;
import java.util.List;

/**
 * @Kadrić Nerma
 * Interface for Employee domain bean as Dao

 */
public interface EmployeeDao extends Dao<Employee> {
    List<Employee> searchByDepartment(Departments dep);
    List<Employee> searchByProject(Project p);
    List<Employee> searchByEducation(String ed);
}
