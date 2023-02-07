package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

/**
 * @author Kadric Nerma
 * DAO interface for User domain bean.
 */
public interface UsersDao extends Dao<Users>{
    public boolean validate(String u, String p);
    public Users searchByPass(String p) throws EmployeeException;
}
