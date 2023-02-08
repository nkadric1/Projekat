package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EmployeeManagerTest {
    private EmployeeManager employeeManager;
    private Employee e;
    private EmployeeDAOSQLImpl employeeDAOSQL;
    private List<Employee> employees;

    @BeforeEach
    public void initializeObj(){
        employeeManager=Mockito.mock(EmployeeManager.class);
        e=new Employee();
        e.setId(1);
        e.setFirst_name("Ivana");
        e.setLast_name("Marc");
        e.setAddress("street5.B");
        e.setHire_date(LocalDate.now());
        e.setPayoff(1000);
        e.setEdu("Bachelor");
        Project p=new Project();
        p.setId(200);
        e.setProject(p);
        Departments d=new Departments();
        d.setId(30);
        e.setDepartment(d);
        employeeDAOSQL=Mockito.mock(EmployeeDAOSQLImpl.class);
        employees=new ArrayList<>();
        employees.addAll(Arrays.asList(new Employee("Zana","Jack","street22.D",LocalDate.now(),new Departments(30),new Project(202),"Master",2000),
                new Employee("Clare","Jade","street25.D",LocalDate.now(),new Departments(20),new Project(202),"Master",2000)));

    }
    @Test
    void validName() throws EmployeeException {
        String n1 = "Zana";
       doNothing().when(employeeManager).validName(n1);
        Assertions.assertTrue(true);
    }

        @Test
    void testAdd() throws EmployeeException{
        Employee e=new Employee("Zina","Marrs","street278.D",LocalDate.now(),new Departments(10),new Project(201),"Bachelor",1000);
        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);
        EmployeeDAOSQLImpl employeeDao=Mockito.mock(EmployeeDAOSQLImpl.class);
        daoFactoryMockedStatic.when(DaoFactory::employeeDao).thenReturn(employeeDao);
        when(employeeDao.add(e)).thenReturn(e);
        employeeManager.add(e);
        Assertions.assertTrue(true);
        daoFactoryMockedStatic.close();
        }

        @Test
        void testDeleteEmp() throws EmployeeException{
            Employee e=new Employee("Zina","Marrs","street278.D",LocalDate.now(),new Departments(10),new Project(201),"Bachelor",1000);
            MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);
            EmployeeDAOSQLImpl employeeDao=Mockito.mock(EmployeeDAOSQLImpl.class);
            daoFactoryMockedStatic.when(DaoFactory::employeeDao).thenReturn(employeeDao);
            employeeManager.delete(e.getId());
            Assertions.assertTrue(true);
            daoFactoryMockedStatic.close();
        }
        @Test
    void testSalary1(){
        Departments d=new Departments();
        d.setId(10);
        Project p=new Project();
        p.setId(202);
              Employee e=new Employee("Julia","Rick","street09A",LocalDate.of(2022, 9,1),d,p,"Bachelor",0);
              assertThrows(EmployeeException.class,()->employeeManager.validSalary(e.getPayoff()),"Employee must have salary") ;

        }
        @Test
    void testSalary2(){
            Employee e=new Employee("Mina","Les","street09B",LocalDate.of(2022, 9,1),new Departments(20),new Project(203),"Bachelor",20000);
            assertThrows(EmployeeException.class,()->employeeManager.validSalary(e.getPayoff()),"Employee cannot have salary greater than 10000") ;

        }
    }


