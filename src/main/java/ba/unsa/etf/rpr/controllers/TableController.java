package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class TableController {
    private EmployeeDAOSQLImpl employeeDAOSQL=new EmployeeDAOSQLImpl();
    public TableView empTable;
    public TableColumn<Employee, Integer> empIdcol;
    public TableColumn<Employee, String> empNamecol;
    public TableColumn<Employee, Date> emphdatecol;
 public void initialize(){
     empIdcol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
     empNamecol.setCellValueFactory(new PropertyValueFactory<Employee,String>("First_name"));
     emphdatecol.setCellValueFactory(new PropertyValueFactory<Employee,Date>("Hire_date"));
     try {
         empTable.setItems(FXCollections.observableList(employeeDAOSQL.getAll()));
         empTable.refresh();
     }catch (Exception e){
         e.printStackTrace();
     }
 }
}
