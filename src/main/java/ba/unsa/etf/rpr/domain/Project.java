package ba.unsa.etf.rpr.domain;
import java.util.Objects;
public class Project {
    private int ID;
    private String project_name;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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
