package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

/**
 * @author Kadric Nerma
 * DAO interface for Department domain bean
 */
public interface DepartmentsDao extends Dao<Departments> {

    public Departments ReturnDepartmentForId(int id) throws EmployeeException;
}
