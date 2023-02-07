package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.EmployeeException;

public class UserManager {
    public void validName(String name) throws EmployeeException {
        if (name.length() > 45 || name == null || name.length() < 2)
            throw new EmployeeException("Name of user must be between 2 and 45 chars");

    }
    public Users searchByPass(String p) throws EmployeeException{
        return DaoFactory.usersDao().searchByPass(p);
    }
    public static boolean validate(String u, String p) throws EmployeeException{
        Users uu=DaoFactory.usersDao().searchByPass(p);
        if(uu.getUsername()!=u) return false;
        else return true;
    }
}
