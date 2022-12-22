package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.util.ArrayList;

public class ProjectChooserController {
    private EmployeeDAOSQLImpl employeeDAOSQL;
    private Button project1;
    private Button project2;

    public ProjectChooserController(EmployeeDAOSQLImpl employeeDAOSQL) {
        this.employeeDAOSQL = employeeDAOSQL;
    }

    @FXML
    public void initialize() {

    }

    public void onActionSave(ActionEvent actionEvent) {
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeDAOSQL.getAll();

    }
}
