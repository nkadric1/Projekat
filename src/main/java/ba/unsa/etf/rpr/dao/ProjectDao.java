package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Project;
import java.util.List;
/**
 * @Kadrić Nerma
 * Interface for Project domain bean as Dao

 */
public interface ProjectDao  extends Dao<Project>{
    List<Project> getByProjectName(String name);
}
