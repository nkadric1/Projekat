package ba.unsa.etf.rpr.dao;
import java.util.List;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
/**
 * @author Kadrić Nerma
 * Interface for all DAO classes
 */
public interface Dao<tt> {
    tt getById(int ID)throws EmployeeException;
    tt add(tt x) throws EmployeeException;
    tt update(tt x) throws EmployeeException;
    void delete(int ID) throws EmployeeException;
    List<tt> getAll() throws EmployeeException;
}
