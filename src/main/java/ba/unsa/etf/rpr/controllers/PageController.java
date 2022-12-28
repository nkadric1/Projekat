package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.dao.EmployeeDao;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
    public TableColumn<Employee,Integer> depemp;
    public TableColumn<Employee, String> empNamecol;
    public TableColumn<Employee, Date> emphdatecol;
    public MenuItem close;
    @FXML
    public void closeIt(ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
   // @FXML
 /*   public void saveIt(ActionEvent actionEvent){

        final String sampleText =null;
        for ( Employee e: employeeDAOSQL.getAll()){
            sampleText=e.toString();
        }

        Text sample = new Text(sampleText);

            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog();

            if (file != null) {
                saveTextToFile(sampleText, file);
            }

    }*/
    @FXML
    public void initialize(){
        empIdcol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
        empNamecol.setCellValueFactory(new PropertyValueFactory<Employee,String>("First_name"));
        emphdatecol.setCellValueFactory(new PropertyValueFactory<Employee,Date>("Hire_date"));
        depemp.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("department_id"));
        try {
            emptab.setItems(FXCollections.observableList(employeeDAOSQL.getAll()));
            emptab.refresh();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void addNew(ActionEvent actionEvent) throws EmployeeException {
        Employee e = new Employee();
        e.setFirst_name(fnamefield.getText());
        e.setLast_name(lnamefield.getText());
//         e.setAddress(addressfield.getText());
//        e.setHire_date(new Date(hdatefield.getValue().getYear(), hdatefield.getValue().getMonthValue(), hdatefield.getValue().getDayOfMonth()));
//
         //iz koje biblioteke je valueof
      // if(hdatefield==null) e.setHire_date(Date.valueOf(LocalDate.now()));
       // else e.setHire_date(hdatefield.getValue());
       e.setEdu(edufield.getText());
      employeeDAOSQL.add(e);
    }
@FXML
public void searchEmp(ActionEvent actionEvent) throws EmployeeException {
  Employee e=employeeDAOSQL.getfromID(Integer.parseInt(empidfield.getText()));
    ObservableList<Employee> emp=FXCollections.observableArrayList();
    emp.add(e);
    emptab.setItems(emp);
}
//sta uraditi za update adrese
@FXML
public void updateAdd(ActionEvent actionEvent) throws EmployeeException {
        Employee e=new Employee();
        e.setId(Integer.parseInt(empidfield.getText()));
        e.setAddress(adr.getText());
        employeeDAOSQL.update(e);
}
    public PageController(EmployeeDAOSQLImpl employeeDAOSQL) {
        this.employeeDAOSQL = employeeDAOSQL;
    }
    @FXML
    public void Click2Project(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/pro.fxml"));
      projectController p = new projectController((EmployeeDAOSQLImpl) DaoFactory.employeeDao());
        fxmlLoader.setController(p);
        Parent root = fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Develoop's projects");
        stage.show();
    }
}
