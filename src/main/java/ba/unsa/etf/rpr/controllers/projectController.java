package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ProjectManager;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class projectController {

 public TextField projectname;
 public ListView<Project> listofprojects;
 private ProjectManager manager=new ProjectManager();
 @FXML
    public void initialize(){
  try{
   refreshProjects();
  listofprojects.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{

 ProjectModel projectModel=new ProjectModel();
   projectModel.fromProject(n);
    projectname.textProperty().bindBidirectional(projectModel.pname);

   });
  } catch (EmployeeException e) {
   new Alert(Alert.AlertType.NONE, e.getMessage(),ButtonType.OK).show();

  }
 }
 @FXML
 public void addProject(ActionEvent actionEvent){
  try{
   Project p=new Project();
   p.setProject_name(projectname.getText());
   p=manager.add(p);
   listofprojects.getItems().add(p);
   projectname.setText("");
  }catch (EmployeeException e){
   new Alert(Alert.AlertType.NONE,e.getMessage(),ButtonType.OK).show();
  }
 }
 @FXML
 public void deleteProject(ActionEvent actionEvent){
  try{
   Project p=listofprojects.getSelectionModel().getSelectedItem();
   manager.delete(p.getId());
   listofprojects.getItems().remove(p);
  }catch (EmployeeException e){
   new Alert(Alert.AlertType.NONE,e.getMessage(),ButtonType.OK).show();
  }
 }
 private void refreshProjects() throws EmployeeException{
try{
 listofprojects.setItems(FXCollections.observableList(manager.getAll()));
 projectname.setText("");
}catch (EmployeeException e){
 new Alert(Alert.AlertType.NONE,e.getMessage(), ButtonType.OK).show();
}
 }
 @FXML
 public void closeIt(ActionEvent actionEvent){
  Platform.exit();
  System.exit(0);
 }
 public class ProjectModel {
  public SimpleStringProperty pname = new SimpleStringProperty("");



  public void fromProject(Project p) {

   this.pname.set(p.getProject_name());

  }

  public Project toProject() {
   Project p=new Project();


   p.setProject_name(this.pname.getName());

   return p;
  }
 }
}
