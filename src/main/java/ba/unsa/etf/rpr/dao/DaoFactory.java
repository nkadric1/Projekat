package ba.unsa.etf.rpr.dao;

/**
 * singleton impl of DAOs
 */
public class DaoFactory {
    private static final AttendanceDao attDao=new AttendanceDAOSQLImpl();
    private static final DepartmentDao depDao=new DepartmentDAOSQLImpl();
    private static final EmployeeDao empDao=new EmployeeDAOSQLImpl();
    private static final ProjectDao proDao=new ProjectDAOSQLImpl();
    private DaoFactory(){}
    public static AttendanceDao attendanceDao(){
        return attDao;
    }
    public static DepartmentDao departmentDao(){
        return depDao;
    }
    public static EmployeeDao employeeDao(){
        return empDao;
    }
    public static ProjectDao projectDao(){
        return proDao;
    }
}
