package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.dao.ProjectDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticofProController {
@FXML
private LineChart<?, ?> Chart;
private EmployeeDAOSQLImpl employeeDAOSQL=new EmployeeDAOSQLImpl();

@FXML
    private void initialize() throws EmployeeException {
    XYChart.Series s=new XYChart.Series();
    s.getData().add(new XYChart.Data<>("Project 1", employeeDAOSQL.returnNumberofEmployees(200)));
    s.getData().add(new XYChart.Data<>("Project 2", employeeDAOSQL.returnNumberofEmployees(201)));
    s.getData().add(new XYChart.Data<>("Project 3", employeeDAOSQL.returnNumberofEmployees(202)));
    Chart.getData().addAll(s);
    Chart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");


}

}
