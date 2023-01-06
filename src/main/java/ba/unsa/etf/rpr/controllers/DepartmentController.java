package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DepartmentManager;
import ba.unsa.etf.rpr.domain.Departments;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DepartmentController {
    private DepartmentManager manager=new DepartmentManager();
    public ListView<Departments> departmentListView;
    public TextField depName;
    public TextField hprice;
    private void refreshDepartment() throws EmployeeException{
        try{
            departmentListView.setItems(FXCollections.observableList(manager.getAll()));
        depName.setText("");
        hprice.setText("");
        }catch(EmployeeException e){
            new Alert(Alert.AlertType.NONE,e.getMessage(), ButtonType.OK).show();
        }
    }
    @FXML
    public void initialize(){
        try{
            refreshDepartment();
            departmentListView.getSelectionModel().selectedItemProperty().addListener((obs,o,n)->{

          DepartmentModel departmentModel=new DepartmentModel();
                  departmentModel.fromDepartment(n);
                    depName.textProperty().bindBidirectional(departmentModel.dname);
                 hprice.textProperty().bindBidirectional(departmentModel.hprice);
            });
        } catch (EmployeeException e) {
          new Alert(Alert.AlertType.NONE, e.getMessage(),ButtonType.OK).show();

        }
    }
    @FXML
    public void updateDep(ActionEvent actionEvent){
        try{
            Departments d=departmentListView.getSelectionModel().getSelectedItem();
            d.setDepname(depName.getText());
            d.setHourlypay(Integer.parseInt(hprice.getText()));
            d=manager.update(d);
            refreshDepartment();
        }catch(EmployeeException e){
            new Alert(Alert.AlertType.NONE,e.getMessage(),ButtonType.OK).show();
        }
    }
    @FXML
    public void addDep(ActionEvent actionEvent){
        try{
            Departments d=new Departments();
            d.setDepname(depName.getText());
            d.setHourlypay(Integer.parseInt(hprice.getText()));
            d=manager.add(d);
            departmentListView.getItems().add(d);
            depName.setText("");
            hprice.setText("");
        }catch (EmployeeException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(),ButtonType.OK).show();
        }
    }
    @FXML
    public void deleteDep(ActionEvent actionEvent){
        try{
            Departments d=departmentListView.getSelectionModel().getSelectedItem();
            manager.delete(d.getId());
            departmentListView.getItems().remove(d);
        }catch (EmployeeException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(),ButtonType.OK).show();
        }
    }
    @FXML
    public void closeIt(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
    public class DepartmentModel {
        public SimpleStringProperty hprice = new SimpleStringProperty("");
        public SimpleStringProperty dname = new SimpleStringProperty("");



        public void fromDepartment(Departments d) {
            this.hprice.set(String.valueOf(d.getHourlypay()));
            this.dname.set(d.getDepname());

        }

        public Departments toDepartment() {
         Departments d=new Departments();

            d.setHourlypay(Integer.parseInt(this.hprice.getValue()));

            d.setDepname(this.dname.getName());

            return d;
        }
    }
}

