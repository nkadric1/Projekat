package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UsersDao;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserManagerTest {
    private UserManager userManager;
    @BeforeEach
    public void initializeObj(){
        userManager=new UserManager();
    }
    @Test
    void validateUserExists() throws EmployeeException{
        String username="adm1";
        String password="adm1adm";
        Users users=new Users("adm1", "adm1adm");
        MockedStatic<DaoFactory> daoFactoryMockedStatic= Mockito.mockStatic(DaoFactory.class);
        when(DaoFactory.usersDao()).thenReturn(Mockito.mock(UsersDao.class));
        when(DaoFactory.usersDao().searchByPass(password)).thenReturn(users);
        assertTrue(UserManager.validate(username,password));
    }
    @Test
    void test() throws EmployeeException{
        List<Users> list=userManager.searchByName("aaa");
        assertTrue(list.isEmpty());


    }
}
