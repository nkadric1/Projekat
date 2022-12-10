package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Departments;
import java.util.List;
public interface DepartmentDao extends Dao<Departments> {


    Departments ReturnDepartmentForId(int id);
}
