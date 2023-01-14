package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.dao.ProjectDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatisticofProController {

private BarChart<String, Number> barChart;
private CategoryAxis catAxis;
private NumberAxis numAxis;
private ObservableList<String> projectNames= FXCollections.observableArrayList();
private ProjectDAOSQLImpl projectDAOSQL=new ProjectDAOSQLImpl();
private EmployeeDAOSQLImpl employeeDAOSQL=new EmployeeDAOSQLImpl();
 private XYChart.Series<String ,Integer> s=new XYChart.Series<>();
@FXML
    private void initialize() throws EmployeeException {

 catAxis=new CategoryAxis();
    List<Project> plist=projectDAOSQL.getAll();
    List<XYChart.Data<String,Number>> d=new ArrayList<>(plist.size());

    for(Project x:plist){
        projectNames.add(Arrays.asList(x.getProject_name()).toString());
          } catAxis.setCategories(projectNames);
    d.add(new XYChart.Data<>( catAxis.getAccessibleText(), 20));
    XYChart.Series<String,Number> series=new XYChart.Series<>(FXCollections.observableList(d));
   numAxis=new NumberAxis();

    barChart=new BarChart<>(catAxis,numAxis);
    barChart.setTitle("Statistics");

    barChart.setLegendVisible(false);
    barChart.setCategoryGap(0.0);
    barChart.setBarGap(0.1);
    barChart.setBackground(Background.EMPTY);
    barChart.setVerticalGridLinesVisible(false);
    barChart.getData().add(series);
}
public  void setProjectData(List<Employee> projectData){
//    int[] counter=new int[10];
//    for(Employee p: projectData){
//        int i=p.getProject_id()-1;
//        counter[i]++;
//    }
//
//    for(int j=0;j<counter.length;j++){
//        s.getData().add(new XYChart.Data<>(projectNames.get(j),counter[j]));
//    }
//    barChart.getData().add(s);
}
}
