package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.AttendanceDAOSQLImpl;
import ba.unsa.etf.rpr.dao.DepartmentDAOSQLImpl;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Department;
import ba.unsa.etf.rpr.domain.Employee;

import java.util.Date;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {

        try {
            EmployeeDAOSQLImpl e=new EmployeeDAOSQLImpl();
            Employee em=new Employee(11,"Mark","Twen","street112.C",new Date(),20,202,30,"Bachelor",1035);
            e.add(em);

        }catch(Exception e){
            System.out.println();
        }
    }
}
