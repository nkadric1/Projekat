package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.dao.EmployeeDao;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class PageController {
    public TextField empidfield;
    public TextField fnamefield;
    public TextField lnamefield;
    public TextField addressfield;
    public DatePicker hdatefield;
    public TextField depfield;
    public TextField adr;
    public TextField edufield;
    private EmployeeDAOSQLImpl employeeDAOSQL=new EmployeeDAOSQLImpl();
    public TableView emptab;
    public TableColumn<Employee, Integer> empIdcol;
    public TableColumn<Employee, String> empNamecol;
    public TableColumn<Employee, Date> emphdatecol;
    @FXML
    public void initialize(){
        empIdcol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
        empNamecol.setCellValueFactory(new PropertyValueFactory<Employee,String>("First_name"));
        emphdatecol.setCellValueFactory(new PropertyValueFactory<Employee,Date>("Hire_date"));
        try {
            emptab.setItems(FXCollections.observableList(employeeDAOSQL.getAll()));
            emptab.refresh();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void addNew(ActionEvent actionEvent){
        Employee e = new Employee();
        e.setFirst_name(fnamefield.getText());
        e.setLast_name(lnamefield.getText());
         e.setAddress(addressfield.getText());
         //iz koje biblioteke je valueof
       // if(hdatefield==null) e.setHire_date(LocalDate.now());
       // else e.setHire_date(hdatefield.getValue());
       e.setEdu(edufield.getText());
      employeeDAOSQL.add(e);
    }
@FXML
public void searchEmp(ActionEvent actionEvent){
  Employee e=employeeDAOSQL.getfromID(Integer.parseInt(empidfield.getText()));
    ObservableList<Employee> emp=FXCollections.observableArrayList();
    emp.add(e);
    emptab.setItems(emp);
}
//sta uraditi za update adrese
@FXML
public void updateAdd(ActionEvent actionEvent){
        Employee e=new Employee();
        e.setId(Integer.parseInt(empidfield.getText()));
        e.setAddress(adr.getText());
        employeeDAOSQL.update(e);
}
    public PageController(EmployeeDAOSQLImpl employeeDAOSQL) {
        this.employeeDAOSQL = employeeDAOSQL;
    }
}
