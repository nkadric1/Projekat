package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

import java.util.List;

/**
 * @author Kadric Nerma
 * DAO interface for User domain bean.
 */
public interface UsersDao extends Dao<Users> {
    boolean validate(String u, String p);

    Users searchByPass(String p) throws EmployeeException;

    List<Users> searchByName(String name) throws EmployeeException;
}
