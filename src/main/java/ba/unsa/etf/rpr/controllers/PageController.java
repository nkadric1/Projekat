package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PageController {
    private static final String filename = "ListOfEmployees.txt";
    public TextField empidfield;
    public TextField fnamefield;
    public TextField lnamefield;
    public TextField addressfield;
    public DatePicker hdatefield;
   public ChoiceBox<String> choiceB;
    public TextField depfield;
    int brojac = 0;
 public  TextField proid;
 //   public TextField adr;
    public TextField edufield;
    public TextField profield;
    public TextField salfield;
    private EmployeeDAOSQLImpl employeeDAOSQL = new EmployeeDAOSQLImpl();
    private DepartmentsDAOSQLImpl depSql = new DepartmentsDAOSQLImpl();
    private TilePane t = new TilePane();
    private List<Departments> depList;
    public TableView<Employee> emptab;
    public TableColumn<Employee, Integer> empIdcol;
    public TableColumn<Employee, Integer> depemp;
    public TableColumn<Employee, String> empNamecol;
    public TableColumn<Employee, Date> emphdatecol;
    public TableColumn<Employee,Integer> procolumn;
public TableColumn<Employee,Integer> salcol;
    public MenuItem close;
private EmployeeManager manager=new EmployeeManager();
    @FXML
    public void openED(ActionEvent actionEvent) {
        openDialog("Manage departments", "/FXML/dep.fxml", new DepartmentController());
    }
    @FXML
    public void openEP(ActionEvent actionEvent){
        openDialog("Manage projects", "/FXML/pro.fxml", new projectController());
    }
    @FXML
    public void getStatisticofProjects(ActionEvent actionEvent){
        openDialog("Statistics of projects", "/FXML/statistic.fxml", new StatisticofProController());
    }
    @FXML
    public void OnAbout(ActionEvent actionEvent){
        openDialog("About","/FXML/about.fxml",null);
    }

    private void openDialog(String title, String file, Object controller) {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource(file));
            l.setController(controller);
            Stage s = new Stage();
            s.setScene(new Scene(l.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            s.setTitle(title);
            s.initStyle(StageStyle.UTILITY);
            s.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    @FXML
    public void closeIt(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void saveIt(ActionEvent actionEvent) throws IOException, EmployeeException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Employee> iterator = employeeDAOSQL.getByHireDate().iterator();
            while (iterator.hasNext()) {
                Employee temp = iterator.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s", temp.getFirst_name(), temp.getLast_name(), temp.getAddress(), temp.getHire_date(),temp.getEdu(),temp.getPayoff()));
                bw.newLine();
            }

        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    @FXML
    public void openFile(ActionEvent actionEvent) {
        try {
            File file = new File("ListOfEmployees.txt");
            if (!Desktop.isDesktopSupported()) {

                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (file.exists())
                desktop.open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        empIdcol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        empNamecol.setCellValueFactory(new PropertyValueFactory<Employee, String>("First_name"));
        emphdatecol.setCellValueFactory(new PropertyValueFactory<Employee, Date>("Hire_date"));
        depemp.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("department_id"));
       salcol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("payoff"));
        procolumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("project_id"));

        emptab.getSelectionModel().selectedItemProperty().addListener((obs, oldEmployee, newEmployee) -> {
            EmployeeModel employeeModel = new EmployeeModel();
            Employee employee=newEmployee;
            employeeModel.fromEmployee(employee);
            fnamefield.textProperty().bindBidirectional(employeeModel.fname);
            lnamefield.textProperty().bindBidirectional(employeeModel.lname);
            addressfield.textProperty().bindBidirectional(employeeModel.address);
            hdatefield.valueProperty().bindBidirectional(employeeModel.hdate);
            depfield.textProperty().bindBidirectional(employeeModel.did);
            profield.textProperty().bindBidirectional(employeeModel.pid);
            edufield.textProperty().bindBidirectional(employeeModel.edu);
            salfield.textProperty().bindBidirectional(employeeModel.sal);
        });
        try {
            emptab.setItems(FXCollections.observableList(employeeDAOSQL.getAll()));
          refreshEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addNew(ActionEvent actionEvent) throws EmployeeException {

        try{
            Employee e = new Employee();
        e.setFirst_name(fnamefield.getText());
        e.setLast_name(lnamefield.getText());
        e.setAddress(addressfield.getText());
        if (hdatefield.getValue() == null) {
            e.setHire_date(LocalDate.now());
        } else {
            e.setHire_date(hdatefield.getValue());
        }
        e.setDepartment_id(Integer.parseInt(depfield.getText()));
        e.setProject_id(Integer.parseInt(profield.getText()));
        e.setEdu(edufield.getText());
        e.setPayoff(Integer.parseInt(salfield.getText()));
        e=manager.add(e);
        emptab.getItems().add(e);
        emptab.refresh();
        fnamefield.setText("");
        lnamefield.setText("");
        addressfield.setText("");
        hdatefield.setValue(null);
        depfield.setText("");
        profield.setText("");
        edufield.setText("");
        salfield.setText("");
        }catch (EmployeeException e)
        {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    //ispraviti metodu za update
    @FXML
    public void UpdateEmp(ActionEvent actionEvent) throws  EmployeeException{
try{
    Employee e= emptab.getSelectionModel().getSelectedItem();
    emptab.getSelectionModel().select(null);
    e.setFirst_name(fnamefield.getText());
    e.setLast_name(lnamefield.getText());
    e.setAddress(addressfield.getText());
    if (hdatefield.getValue() == null) {
        e.setHire_date(LocalDate.now());
    } else {
        e.setHire_date(hdatefield.getValue());
    }
    try{
    DepartmentsDAOSQLImpl d=new DepartmentsDAOSQLImpl();
    Departments dep=d.ReturnDepartmentForId(Integer.parseInt(depfield.getText()));
        e.setDepartment_id(Integer.parseInt(depfield.getText()));
      //  try{
//            ProjectDAOSQLImpl p=new ProjectDAOSQLImpl();
//            Project pro=p.getById(Integer.parseInt(profield.getText()));
            e.setProject_id(Integer.parseInt(profield.getText()));
            e.setEdu(edufield.getText());
            e.setPayoff(Integer.parseInt(salfield.getText()));
            e=manager.update(e);
            refreshEmployees();
//        }
//    catch(Exception p){
//        new Alert(Alert.AlertType.NONE,"Project ID is not valid.",ButtonType.OK).show();;
//    }
    }
   catch (Exception d){
        new Alert(Alert.AlertType.NONE,"Department ID is not valid.",ButtonType.OK).show();;
   }
} catch (Exception e) {
    throw new RuntimeException(e);
}
    }

    @FXML
    public void searchEmp(ActionEvent actionEvent) throws EmployeeException {
        Employee e = employeeDAOSQL.getfromID(Integer.parseInt(empidfield.getText()));
        ObservableList<Employee> emp = FXCollections.observableArrayList();
        emp.add(e);
        emptab.setItems(emp);
    }


@FXML
public void DeleteEmp(ActionEvent actionEvent) throws EmployeeException{
    try {
        Employee ee= (Employee) emptab.getSelectionModel().getSelectedItem();
        manager.delete(ee.getId());
        emptab.getItems().remove(ee);
    }catch (EmployeeException e){
        new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
    }
}
@FXML
  public void getListofemp(ActionEvent actionEvent) throws EmployeeException{
List<Employee> e=employeeDAOSQL.searchByProject(Integer.parseInt(proid.getText()));
ObservableList<Employee> emp=FXCollections.observableArrayList();
emp.addAll(e);
     emptab.setItems(emp);

  }

    public PageController(EmployeeDAOSQLImpl employeeDAOSQL) {
        this.employeeDAOSQL = employeeDAOSQL;
    }
    public PageController(){}
    private void refreshEmployees() throws EmployeeException{
        try{
            emptab.setItems(FXCollections.observableList(manager.getAll()));
            fnamefield.setText("");
            lnamefield.setText("");
            addressfield.setText("");
            hdatefield.setValue(null);
            depfield.setText("");
            profield.setText("");
            edufield.setText("");
            salfield.setText("");
        }catch (EmployeeException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    public class EmployeeModel {
        public SimpleStringProperty fname = new SimpleStringProperty("");
        public SimpleStringProperty lname = new SimpleStringProperty("");
        public SimpleStringProperty address = new SimpleStringProperty("");
        public SimpleObjectProperty<LocalDate> hdate = new SimpleObjectProperty<LocalDate>();
        public SimpleStringProperty did = new SimpleStringProperty("");
        public SimpleStringProperty pid=new SimpleStringProperty("");
        public SimpleStringProperty edu = new SimpleStringProperty("");
        public SimpleStringProperty sal=new SimpleStringProperty("");


        public void fromEmployee(Employee e) {
            this.fname.set(e.getFirst_name());
            this.lname.set(e.getLast_name());
            this.address.set(e.getAddress());
            this.hdate.set(e.getHire_date());
            this.did.set(String.valueOf(e.getDepartment_id()));
            this.pid.set(String.valueOf(e.getProject_id()));
            this.edu.set(e.getEdu());
            this.sal.set(String.valueOf(e.getPayoff()));

        }

        public Employee toEmployee() {
            Employee e = new Employee();
            e.setFirst_name(this.fname.getName());
            e.setLast_name(this.lname.getName());
            e.setAddress(this.address.getName());
            e.setDepartment_id(Integer.parseInt(this.did.getValue()));
            e.setProject_id(Integer.parseInt(this.pid.getValue()));
            e.setHire_date(this.hdate.getValue());
            e.setEdu(this.edu.getName());
            e.setPayoff(Integer.parseInt(this.sal.getValue()));
            return e;
        }
    }
}
