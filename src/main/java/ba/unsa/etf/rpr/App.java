package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DepartmentDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Department;


public class App 
{
    public static void main( String[] args )
    {

        try {
            DepartmentDAOSQLImpl d= new DepartmentDAOSQLImpl();
            System.out.println("OK");
            System.out.println(d.ReturnDepartmentForId(20));
        }catch(Exception e){
            System.out.println();
        }
    }
}
