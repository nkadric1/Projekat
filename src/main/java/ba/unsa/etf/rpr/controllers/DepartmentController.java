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

/**
 * @author Kadric Nerma
 * Department-controller is controller about page of departments. Adding and deleting can be done in this controller.
 * Administrator have insight into departments of this company.
 */

public class DepartmentController {
    private DepartmentModel departmentModel = new DepartmentModel();
    private DepartmentManager manager = new DepartmentManager();
    public ListView<Departments> departmentListView;
    public TextField depName;
    public TextField hprice;

    /**
     * This method is used to set all items in the listview, and then clears all fields.
     */
    private void refreshDepartment() throws EmployeeException {
        try {
            departmentListView.setItems(FXCollections.observableList(manager.getAll()));
            depName.setText("");
            hprice.setText("");
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * The initialize method handles options passed to the class upon creation.
     * It also handles any other setup that may be required when the class is created.
     * Initialize is called automatically when we open the application so we won’t call it directly.
     * This method populates the listview and binds bidirectionally to the selected items.
     */
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

    /**
     * After filling the field which we want to update, we click on the update button and then this method is called.
     * It is used to update the department.
     *
     * @param actionEvent
     */
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

    /**
     * After filling the fields for new department, we click on the add button and then this method is called.
     * It is used to add a new department to the database.
     *
     * @param actionEvent
     */
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

    /**
     * This method is used to delete the department that is selected in the listview
     *
     * @param actionEvent
     */
    @FXML
    public void deleteDep(ActionEvent actionEvent) {
        try {
            manager.delete(departmentListView.getSelectionModel().getSelectedItem().getId());
            refreshDepartment();
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * This method is used to close this stage
     *
     * @param actionEvent
     */
    @FXML
    public void closeIt(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * This is the inner class of project model.
     * Inner classes are a security mechanism in Java and it is also used to access the private members of a class.
     * It suports two-way data binding with form for Department management.
     */
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

