package ba.unsa.etf.rpr.domain;
import ba.unsa.etf.rpr.dao.ProjectDAOSQLImpl;

import java.util.Objects;
public class Project implements Idable {
    private int ID;
    private String project_name;

    public void setId(int id) {
this.ID=id;
    }


    public int getId() {
        return ID;
    }
    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Project(int ID, String project_name) {
        this.ID = ID;
        this.project_name = project_name;
    }

    public Project() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return ID == project.ID && project_name.equals(project.project_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, project_name);
    }

    @Override
    public String toString() {
        return "Project{" +
                "ID=" + ID +
                ", project_name='" + project_name + '\'' +
                '}';
    }



}
