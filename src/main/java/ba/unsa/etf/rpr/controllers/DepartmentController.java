package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DepartmentManager;
import ba.unsa.etf.rpr.domain.Departments;

import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DepartmentController {
    private DepartmentManager manager=new DepartmentManager();
    public ListView<Departments> departmentListView;
    public TextField depName;
    private void refreshDepartment() throws EmployeeException{
        try{
            departmentListView.setItems(FXCollections.observableList(manager.getAll()));
        depName.setText("");
        }catch(EmployeeException e){
            new Alert(Alert.AlertType.NONE,e.getMessage(), ButtonType.OK).show();
        }
    }
    @FXML
    public void initialize(){
        try{
            refreshDepartment();
            departmentListView.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{
if(n!=null){ depName.setText(n.getDepname()); }
            });
        } catch (EmployeeException e) {
          new Alert(Alert.AlertType.NONE, e.getMessage(),ButtonType.OK).show();

        }
    }
    public void updateDep(ActionEvent actionEvent){
        try{
            Departments d=departmentListView.getSelectionModel().getSelectedItem();
            d.setDepname(depName.getText());
            d=manager.update(d);
            refreshDepartment();
        }catch(EmployeeException e){
            new Alert(Alert.AlertType.NONE,e.getMessage(),ButtonType.OK).show();
        }
    }
    public void addDep(ActionEvent actionEvent){
        try{
            Departments d=new Departments();
            d.setDepname(depName.getText());
            d=manager.add(d);
            departmentListView.getItems().add(d);
            depName.setText("");
        }catch (EmployeeException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(),ButtonType.OK).show();
        }
    }
    public void deleteDep(ActionEvent actionEvent){
        try{
            Departments d=departmentListView.getSelectionModel().getSelectedItem();
            manager.delete(d.getId());
            departmentListView.getItems().remove(d);
        }catch (EmployeeException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(),ButtonType.OK).show();
        }
    }
}
