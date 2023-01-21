package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DepartmentManager;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class DepartmentController {
    private DepartmentModel departmentModel = new DepartmentModel();
    private DepartmentManager manager = new DepartmentManager();
    public ListView<Departments> departmentListView;
    public TextField depName;
    public TextField hprice;

    private void refreshDepartment() throws EmployeeException {
        try {
            departmentListView.setItems(FXCollections.observableList(manager.getAll()));
            depName.setText("");
            hprice.setText("");
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    public void initialize() {
        try {
            refreshDepartment();
        } catch (EmployeeException e) {
            throw new RuntimeException(e);
        }
        depName.textProperty().bindBidirectional(departmentModel.dname);
        hprice.textProperty().bindBidirectional(departmentModel.hprice);
        departmentListView.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {
            if (n != null) departmentModel.fromDepartment(n);
        });
    }
    @FXML
    public void updateDep(ActionEvent actionEvent) {
        try {
            manager.update(departmentModel.toDepartment());
            refreshDepartment();
        } catch (EmployeeException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    @FXML
    public void addDep(ActionEvent actionEvent) {
        try {
            manager.add(departmentModel.toDepartment());
            refreshDepartment();
        } catch (EmployeeException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    @FXML
    public void deleteDep(ActionEvent actionEvent) {
        try {
            manager.delete(departmentListView.getSelectionModel().getSelectedItem().getId());
            refreshDepartment();
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
    public class DepartmentModel {
        public SimpleIntegerProperty id = new SimpleIntegerProperty();
        public SimpleStringProperty hprice = new SimpleStringProperty("");
        public SimpleStringProperty dname = new SimpleStringProperty("");
        public void fromDepartment(Departments d) {
            this.id.set(d.getId());
            this.hprice.set(String.valueOf(d.getHourlypay()));
            this.dname.set(d.getDepname());
        }
        public Departments toDepartment() {
            Departments d = new Departments();
            d.setId(this.id.getValue());
            d.setHourlypay(Integer.parseInt(this.hprice.getValue()));
            d.setDepname(this.dname.getValue());
            return d;
        }
    }
}

