package ba.unsa.etf.rpr.business;


import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Kadric Nerma
 * JUNIT tests for project class
 */
public class ProjectManagerTest {
    private ProjectManager projectManager=new ProjectManager();

    @Test
    void validName(){
        String projectname="P";
        EmployeeException ee=Assertions.assertThrows(EmployeeException.class, ()->{projectManager.validName(projectname);},"Name of project must be between 2 and 45 chars");
        Assertions.assertEquals("Name of project must be between 2 and 45 chars", ee.getMessage());

    }
    @Test
    void addProject() {
        Project p=new Project();
        p.setId(1);
        p.setProject_name("Mobile application");
        assertThrows(EmployeeException.class, ()->projectManager.add(p),"ID is autogenerated. Cannot add project.");
    }
    @Test
    void updateProject() throws EmployeeException{
        List<Project> list=projectManager.getAll();
        list.get(0).setProject_name("Application..");
      projectManager.update(list.get(0));
      Assertions.assertTrue(true);
    }

}
