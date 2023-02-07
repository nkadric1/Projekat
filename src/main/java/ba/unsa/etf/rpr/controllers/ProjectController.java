package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProjectManager;
import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.bytebuddy.agent.VirtualMachine;

import java.util.Collections;
import java.util.List;

/** @author Kadric Nerma
 * Project-controller is controller about page of projects. Adding and  deleting can be done in this controller.
*  Administrator have insight into projects of this company.
 */
public class ProjectController {
    public TextField projectname;
    public ListView<Project> listofprojects;
    private ProjectModel projectModel = new ProjectModel();
    private ProjectManager manager = new ProjectManager();
    /**
     * The initialize method handles options passed to the class upon creation.
     * It also handles any other setup that may be required when the class is created.
     * Initialize is called automatically when we open the application so we won’t call it directly.
     * This method populates the listview and binds bidirectionally to the selected items.
     */
    @FXML
    public void initialize() {
        try {
            refreshProjects();
            projectname.textProperty().bindBidirectional(projectModel.pname);
            listofprojects.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
                if (n != null) {
                    projectModel.fromProject(n);

                }
            });
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /** After filling the fields for new project, we click on the add button and then this method is called.
     * It is used to add a new project to the database.
     * @param actionEvent
     */
    @FXML
    public void addProject(ActionEvent actionEvent) {
        try {
            manager.add(projectModel.toProject());
            refreshProjects();
        } catch (EmployeeException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * This method is used to delete the project that is selected in the listview
     * @param actionEvent
     */
    @FXML
    public void deleteProject(ActionEvent actionEvent) {
        try {
            manager.delete(listofprojects.getSelectionModel().getSelectedItem().getId());
            refreshProjects();
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * This method is used to set all items in the listview, and then clears all fields.
     */
    private void refreshProjects() throws EmployeeException {
        try {

            listofprojects.setItems(FXCollections.observableList(manager.getAll()));

            projectname.setText("");
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }

    }
    /**
     * This method is used to close this stage
     * @param actionEvent
     */
    @FXML
    public void closeIt(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
    /** This is the inner class of project model.
     * Inner classes are a security mechanism in Java and it is also used to access the private members of a class.
     * It suports two-way data binding with form for Project management.
     */
    public class ProjectModel {
        public SimpleIntegerProperty id = new SimpleIntegerProperty();
        public SimpleStringProperty pname = new SimpleStringProperty("");
        public void fromProject(Project p) {
            this.id.set(p.getId());
            this.pname.set(p.getProject_name());

        }
        public Project toProject() {
            Project p = new Project();
            p.setId(this.id.getValue());
            p.setProject_name(this.pname.getValue());
            return p;
        }
    }
}
