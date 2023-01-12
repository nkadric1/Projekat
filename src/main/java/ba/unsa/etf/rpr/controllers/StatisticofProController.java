package ba.unsa.etf.rpr.controllers;

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

import java.util.Arrays;
import java.util.List;

public class StatisticofProController {
private BarChart<String, Integer> barChart;
private CategoryAxis catAxis;
private NumberAxis numAxis;
private ObservableList<String> projectNames= FXCollections.observableArrayList();
private ProjectDAOSQLImpl projectDAOSQL=new ProjectDAOSQLImpl();
@FXML
    private void initialize() throws EmployeeException {

    List<Project> plist=projectDAOSQL.getAll();
    for(Project x:plist){
        projectNames.add(Arrays.asList(x.getProject_name()).toString());
    }
    catAxis.setCategories(projectNames);
}
public  void setProjectData(List<Employee> projectData){
    int[] counter=new int[10];
    for(Employee p: projectData){
        int i=p.getProject_id()-1;
        counter[i]++;
    }
    XYChart.Series<String,Integer> s=new XYChart.Series<>();
    for(int j=0;j<counter.length;j++){
        s.getData().add(new XYChart.Data<>(projectNames.get(j),counter[j]));
    }
    barChart.getData().add(s);
}
}
