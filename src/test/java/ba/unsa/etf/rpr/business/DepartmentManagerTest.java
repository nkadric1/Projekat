package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.DepartmentsDAOSQLImpl;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class DepartmentManagerTest {
    private  DepartmentManager departmentManager;
    private Departments d;
    private DepartmentsDAOSQLImpl departmentsDAOSQL;
    private List<Departments> departments;
    @BeforeEach
    public void initializeObj(){
       departmentManager= new DepartmentManager();
        Mockito.mock(DepartmentManager.class);
        d=new Departments();
        d.setDepname("Backend-developer");
      d.setHourlypay(15);
    departmentsDAOSQL=Mockito.mock(DepartmentsDAOSQLImpl.class);
        departments=new ArrayList<>();
     departments.addAll(Arrays.asList(new Departments(51,"Marketing",10),
                new Departments(52,"Software archi",20)));

    }
    @Test
    void addNewDepTest() throws EmployeeException{
        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);

 DepartmentsDAOSQLImpl depDao=Mockito.mock(DepartmentsDAOSQLImpl.class);
        daoFactoryMockedStatic.when(DaoFactory::departmentDao).thenReturn(depDao);

        when(depDao.add(d)).thenReturn(d);

       departmentManager.add(d);
        Assertions.assertTrue(true);
    }
    @Test
    void UpdateDepTest() throws EmployeeException{
        d.setHourlypay(23);
        MockedStatic<DaoFactory> daoFactoryMockedStatic=Mockito.mockStatic(DaoFactory.class);

        DepartmentsDAOSQLImpl depDao=Mockito.mock(DepartmentsDAOSQLImpl.class);
        daoFactoryMockedStatic.when(DaoFactory::departmentDao).thenReturn(depDao);

        when(depDao.update(d)).thenReturn(d);

        departmentManager.update(d);
        Assertions.assertTrue(true);
    }
}
