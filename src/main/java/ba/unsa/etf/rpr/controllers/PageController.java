package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.DepartmentsDAOSQLImpl;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
    public TextField adr;
    public TextField edufield;
    private EmployeeDAOSQLImpl employeeDAOSQL = new EmployeeDAOSQLImpl();
    private DepartmentsDAOSQLImpl depSql = new DepartmentsDAOSQLImpl();
    private TilePane t = new TilePane();
    private List<Departments> depList;
    public TableView emptab;
    public TableColumn<Employee, Integer> empIdcol;
    public TableColumn<Employee, Integer> depemp;
    public TableColumn<Employee, String> empNamecol;
    public TableColumn<Employee, Date> emphdatecol;
    public MenuItem close;

    @FXML
    public void openED(ActionEvent actionEvent) {
        openDialog("Manage departments", "/FXML/dep.fxml", new DepartmentController());
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
                bw.write(String.format("%s\t%s\t%s", temp.getFirst_name(), temp.getLast_name(), temp.getAddress()));
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
        try {
            emptab.setItems(FXCollections.observableList(employeeDAOSQL.getAll()));
            emptab.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            depList = depSql.getAll();
//            choiceB = new ChoiceBox<>(FXCollections.observableList(depList));
//            t = new TilePane();
//            t.getChildren().add(choiceB);
            choiceB = new ChoiceBox<>();
            choiceB.getItems().addAll("item1", "item2", "item3");
//            choiceB.setValue(depList.get(0));
//            Scene screen = new Scene(t, 400, 300);
        } catch (EmployeeException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addNew(ActionEvent actionEvent) throws EmployeeException {
        Employee e = new Employee();
        e.setFirst_name(fnamefield.getText());
        e.setLast_name(lnamefield.getText());
        e.setAddress(addressfield.getText());
        if (hdatefield.getValue() == null) {
            e.setHire_date(LocalDate.now());
        } else {
            e.setHire_date(hdatefield.getValue());
        }


        //iz koje biblioteke je valueof
        // if(hdatefield==null) e.setHire_date(Date.valueOf(LocalDate.now()));
        // else e.setHire_date(hdatefield.getValue());
        e.setEdu(edufield.getText());
        employeeDAOSQL.add(e);
    }

    @FXML
    public void searchEmp(ActionEvent actionEvent) throws EmployeeException {
        Employee e = employeeDAOSQL.getfromID(Integer.parseInt(empidfield.getText()));
        ObservableList<Employee> emp = FXCollections.observableArrayList();
        emp.add(e);
        emptab.setItems(emp);
    }

    //sta uraditi za update adrese
    @FXML
    public void updateAdd(ActionEvent actionEvent) throws EmployeeException {
        Employee e = new Employee();
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
        Stage stage = new Stage();
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setTitle("Develoop's projects");
        stage.show();
    }

    public class EmployeeModel {
        public SimpleStringProperty fname = new SimpleStringProperty("");
        public SimpleStringProperty lname = new SimpleStringProperty("");
        public SimpleStringProperty address = new SimpleStringProperty("");
        public SimpleObjectProperty<LocalDate> hdate = new SimpleObjectProperty<LocalDate>();
        public SimpleIntegerProperty did = new SimpleIntegerProperty();
        public SimpleStringProperty edu = new SimpleStringProperty("");

        //public SimpleIntegerProperty pay=new SimpleIntegerProperty();
        public void fromEmployee(Employee e) {
            this.fname.set(e.getFirst_name());
            this.lname.set(e.getLast_name());
            this.address.set(e.getAddress());
            this.hdate.set(e.getHire_date());
            this.did.set(e.getDepartment_id());
            this.edu.set(e.getEdu());
            //   this.pay.set(e.getPayoff());
        }

        public Employee toEmployee() {
            Employee e = new Employee();
            e.setFirst_name(this.fname.getName());
            e.setLast_name(this.lname.getName());
            e.setAddress(this.address.getName());
            e.setDepartment_id(this.did.getValue());
            e.setHire_date(this.hdate.getValue());
            e.setEdu(this.edu.getName());
            return e;
        }
    }
}
