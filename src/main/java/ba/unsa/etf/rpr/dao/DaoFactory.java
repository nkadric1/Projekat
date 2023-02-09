package ba.unsa.etf.rpr.dao;

/**
 * @author Kadric Nerma
 * Factory method for singleton implementation of DAOs
 */
public class DaoFactory {

    private static final DepartmentsDao depDao = DepartmentsDAOSQLImpl.getInstance();
    private static final EmployeeDao empDao = EmployeeDAOSQLImpl.getInstance();
    private static final ProjectDao proDao = ProjectDAOSQLImpl.getInstance();
    private static final UsersDao userDao = UsersDAOSQLImpl.getInstance();

    private DaoFactory() {
    }

    public static DepartmentsDao departmentDao() {
        return depDao;
    }

    public static EmployeeDao employeeDao() {
        return empDao;
    }

    public static ProjectDao projectDao() {
        return proDao;
    }

    public static UsersDao usersDao() {
        return userDao;
    }
}
