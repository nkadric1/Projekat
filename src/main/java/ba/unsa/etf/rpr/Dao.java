package ba.unsa.etf.rpr;
import java.util.List;

/**
 * @author Kadrić Nerma
 * Interface for all DAO classes
 */
public interface Dao<tt> {
    tt getById(int ID);
    tt add(tt x);
    tt update(tt x);
    void delete(int ID);
    List<tt> getAll();
}
