package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;

import java.util.List;

import ba.unsa.etf.rpr.exceptions.EmployeeException;

/**
 * @Kadrić Nerma
 * DAO interface for Employee domain bean;
 * three methods that helps user to get informations about employees;
 * to search employees by department in which they work;
 * to search employees by project on which they work;
 * to get employees by date they started to work;
 */
public interface EmployeeDao extends Dao<Employee> {
    int returnNumberofEmployees(int id) throws EmployeeException;


    List<Employee> searchByDepartment(int Id) throws EmployeeException;

    List<Employee> searchByProject(int id) throws EmployeeException;

    List<Employee> getByHireDate() throws EmployeeException;

    Employee getfromID(int id) throws EmployeeException;


}
