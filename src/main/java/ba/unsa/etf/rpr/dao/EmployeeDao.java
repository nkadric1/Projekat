package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;

import java.util.List;

import ba.unsa.etf.rpr.exceptions.EmployeeException;

/**
 * @Kadrić Nerma
 * Interface for Employee domain bean as Dao
 * three methods that helps user to get informations about employees
 * to search employees by department in which they work
 * to search employees by project on which they work
 * to get employees by date they started to work
 */
public interface EmployeeDao extends Dao<Employee> {
    public int returnNumberofEmployees(int id) throws EmployeeException;


    public List<Employee> searchByDepartment(int Id) throws EmployeeException;

    public List<Employee> searchByProject(int id) throws EmployeeException;

    public List<Employee> getByHireDate() throws EmployeeException;

    public Employee getfromID(int id) throws EmployeeException;


}
