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

public class projectController {
    public TextField projectname;
    public ListView<Project> listofprojects;
    private ProjectModel projectModel = new ProjectModel();
    private ProjectManager manager = new ProjectManager();
    @FXML
    public void initialize() {
        try {
            refreshProjects();
            projectname.textProperty().bindBidirectional(projectModel.pname);
            listofprojects.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
                if (n != null) projectModel.fromProject(n);
            });
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
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
    @FXML
    public void deleteProject(ActionEvent actionEvent) {
        try {
            manager.delete(listofprojects.getSelectionModel().getSelectedItem().getId());
            refreshProjects();
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    private void refreshProjects() throws EmployeeException {
        try {
            listofprojects.setItems(FXCollections.observableList(manager.getAll()));
            projectname.setText("");
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    @FXML
    public void closeIt(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
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
            p.setProject_name(this.pname.getName());
            return p;
        }
    }
}
