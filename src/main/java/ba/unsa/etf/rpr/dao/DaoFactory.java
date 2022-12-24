package ba.unsa.etf.rpr.dao;

/**
 * singleton impl of DAOs
 */
public class DaoFactory {

    private static final DepartmentDao depDao=new DepartmentDAOSQLImpl();
    private static final EmployeeDao empDao=new EmployeeDAOSQLImpl();
    private static final ProjectDao proDao=new ProjectDAOSQLImpl();
    private static final UsersDao userDao=new UsersDAOSQLImpl();
    private DaoFactory(){}

    public static DepartmentDao departmentDao(){
        return depDao;
    }
    public static EmployeeDao employeeDao(){
        return empDao;
    }
    public static ProjectDao projectDao(){
        return proDao;
    }
    public static UsersDao usersDao(){return userDao;}
}
