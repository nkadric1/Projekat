package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.DepartmentManager;
import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.business.ProjectManager;
import ba.unsa.etf.rpr.domain.Departments;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Project;
import ba.unsa.etf.rpr.exceptions.EmployeeException;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Date;
import java.util.Iterator;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/** @author Kadric Nerma
 * Page-controller is controller about main page of this app. Adding, searching, deleting and more can be done in this controller.
 *  It has two-way connection, the file of current employees can be saved and opened. Administrator have insight into departments and projects of this company.
 */
public class PageController {
    public TextArea txtArea;
    private static final String filename = "ListOfEmployees.txt";
    private EmployeeModel employeeModel = new EmployeeModel();
    public TextField empidfield;
    public TextField fnamefield;
    public TextField lnamefield;
    public TextField addressfield;
    public DatePicker hdatefield;
    public ChoiceBox<Departments> depfield;
    public TextField edufield;
    public ChoiceBox<Project> profield;
    public TextField salfield;

    public TableView<Employee> emptab;
    public TableColumn<Employee, Integer> empIdcol;
    public TableColumn<Employee, String> empNamecol;
    public TableColumn<Employee, Date> emphdatecol;
    public TableColumn<Employee, Departments> depemp;
    public TableColumn<Employee, Project> procolumn;
    public TableColumn<Employee, Integer> salcol;
    public TextField proid;
    public Button addempbttn;
    public Button upempbttn;
    public Button delempbttn;
    public javafx.scene.image.Image img = new Image("IMAGES/add.png");
    public Image img2 = new Image("IMAGES/update.png");
    public Image img3 = new Image("IMAGES/delete.png");
    private EmployeeManager manager = new EmployeeManager();
    private DepartmentManager departmentManager = new DepartmentManager();
    private ProjectManager projectManager=new ProjectManager();

    /**
     * This method calls openDialog to open the new stage of departments and to manipulate them
     * @param actionEvent
     */
    @FXML
    public void openEd(ActionEvent actionEvent) {
        openDialog("Manage departments", "/FXML/dep.fxml", new DepartmentController());
    }

    /**
     * This method calls openDialog to open the new stage of projects and to manipulate them
     * @param actionEvent
     */
    @FXML
    public void openEp(ActionEvent actionEvent) {
        openDialog("Manage projects", "/FXML/pro.fxml", new ProjectController());
    }


    @FXML
    public void onAbout(ActionEvent actionEvent) {
        openDialog("About", "/FXML/about.fxml", null);
    }

    /**
     * This method is used to open a new stage, it is private and is used to open departments and projects stage.
     * @param title - the title of new stage that we want to open
     * @param file - the fxml file,which is linked to that new stage
     * @param controller - the controller that will manage the new stage
     */
    private void openDialog(String title, String file, Object controller) {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource(file));
            l.setController(controller);
            Stage s = new Stage();
            s.setScene(new Scene(l.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            s.setTitle(title);
            s.initStyle(StageStyle.UTILITY);
            s.setResizable(false);
            s.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
            e.printStackTrace();
        }
    }

    /**
     * This method calls refreshEmployees to display all employees in the table
     * @param actionEvent
     * @throws EmployeeException
     */
    @FXML
    public void showAll(ActionEvent actionEvent) throws EmployeeException {
        refreshEmployees();
    }

    /**
     * This method is used to exit of the application
     * @param actionEvent
     */
    @FXML
    public void closeIt(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * It is located in File part
     * It is used to save list of employees as .txt file
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void saveIt(ActionEvent actionEvent) throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<Employee> iterator = null;
            try {
                iterator = manager.getByHireDate().iterator();
            } catch (EmployeeException e) {
                e.printStackTrace();
            }
            while (iterator.hasNext()) {
                Employee temp = iterator.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s\t%s", temp.getFirst_name(), temp.getLast_name(), temp.getAddress(), temp.getHire_date(), temp.getEdu(), temp.getPayoff()));
                bw.newLine();
            }
            txtArea.setText("List of employees has been saved.");
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    /**
     * It is located in File part
     * It is used to open list of employees as .txt file
     * The administrator can enter some additional things related to employees
     * @param actionEvent
     */
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
            txtArea.setText("File has been opened.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The initialize method handles options passed to the class upon creation.
     * It also handles any other setup that may be required when the class is created.
     * Initialize is called automatically when we open the application so we won’t call it directly.
     * This method places images to buttons, sets default text to the text area, populates the table and binds bidirectionally to the selected items.
     */
    @FXML
    public void initialize() {

        txtArea.setText("An insight into the administrative part of the company.");

        try {
            depfield.setItems(FXCollections.observableList(departmentManager.getAll()));
            profield.setItems(FXCollections.observableList(projectManager.getAll()));
        } catch (EmployeeException e) {
            throw new RuntimeException(e);
        }
        addempbttn.setGraphic(new ImageView(img));
        upempbttn.setGraphic(new ImageView(img2));
        delempbttn.setGraphic(new ImageView(img3));
        empIdcol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        empNamecol.setCellValueFactory(new PropertyValueFactory<Employee, String>("First_name"));
        emphdatecol.setCellValueFactory(new PropertyValueFactory<Employee, Date>("Hire_date"));
        depemp.setCellValueFactory(new PropertyValueFactory<Employee, Departments>("department"));
        salcol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("payoff"));
        procolumn.setCellValueFactory(new PropertyValueFactory<Employee, Project>("project"));

        fnamefield.textProperty().bindBidirectional(employeeModel.fname);
        lnamefield.textProperty().bindBidirectional(employeeModel.lname);
        addressfield.textProperty().bindBidirectional(employeeModel.address);
        hdatefield.valueProperty().bindBidirectional(employeeModel.hdate);
        depfield.valueProperty().bindBidirectional(employeeModel.did);
        profield.valueProperty().bindBidirectional(employeeModel.pid);
        edufield.textProperty().bindBidirectional(employeeModel.edu);
        salfield.textProperty().bindBidirectional(employeeModel.sal);
        emptab.getSelectionModel().selectedItemProperty().addListener((obs, oldEmployee, newEmployee) -> {
            if (newEmployee != null) {
                employeeModel.fromEmployee(newEmployee);
            }
        });

        refreshEmployees();
    }

    /** After filling the fields for new employee, we click on the add button and then this method is called.
     * It is used to add a new employee to the database.
     * @param actionEvent
     */
    @FXML
    public void addNew(ActionEvent actionEvent) {
        try {
            manager.add(employeeModel.toEmployee());
            refreshEmployees();
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     *  After filling the field which we want to update, we click on the update button and then this method is called.
     *  It is used to update the employee.
     * @param actionEvent
     */
    @FXML
    public void updateEmp(ActionEvent actionEvent) {
        try {
            Employee e = employeeModel.toEmployee();
            manager.update(e);
            refreshEmployees();
        } catch (Exception d) {
            d.printStackTrace();
            new Alert(Alert.AlertType.NONE, d.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     *  This method is used to search for the employee, whose ID is passed as parameter to getById method, and displays it in the table
     * @param actionEvent
     */
    @FXML
    public void searchEmp(ActionEvent actionEvent) {
        try {
            Employee e = manager.getById(Integer.parseInt(empidfield.getText()));
            ObservableList<Employee> emp = FXCollections.observableArrayList();
            emp.add(e);
            txtArea.setText("Search has been successful!");
            emptab.setItems(emp);
        } catch (EmployeeException e) {
            txtArea.setText("Employee with ID: " + Integer.parseInt(empidfield.getText()) + "\n is not available.");
        }
    }

    /**
     * This method is used to delete the employee that is selected in the table
     * @param actionEvent
     */
    @FXML
    public void deleteEmp(ActionEvent actionEvent) {
        try {
            manager.delete(emptab.getSelectionModel().getSelectedItem().getId());
            txtArea.setText("Employee which ID is " + emptab.getSelectionModel().getSelectedItem().getId() + "\n has been deleted.");
            refreshEmployees();
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /**
     * This method is used to display all employees working on the project whose ID is passed to the getByProject method.
     * @param actionEvent
     * @throws EmployeeException
     */
    @FXML
    public void getListOfEmp(ActionEvent actionEvent) throws EmployeeException {
        try {
            emptab.setItems(FXCollections.observableList(manager.getByProject(Integer.parseInt(proid.getText()))));
        } catch (EmployeeException e) {
            txtArea.setText("Project with ID " + Integer.parseInt(proid.getText()) + "\n is not available.");
        }
    }

    /**
     * This method is used to set all items in the table, and then clears all fields.
     */

    private void refreshEmployees() {
        try {
            emptab.setItems(FXCollections.observableList(manager.getAll()));
            fnamefield.setText("");
            lnamefield.setText("");
            addressfield.setText("");
            hdatefield.setValue(null);
            depfield.setValue(null);
            profield.setValue(null);
            edufield.setText("");
            salfield.setText("");
        } catch (EmployeeException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    /** This is the inner class of employee model.
     * Inner classes are a security mechanism in Java and it is also used to access the private members of a class.
     * It suports two-way data binding with form for Employee management.
     */
    public class EmployeeModel {

        public SimpleIntegerProperty id = new SimpleIntegerProperty();
        public SimpleStringProperty fname = new SimpleStringProperty("");
        public SimpleStringProperty lname = new SimpleStringProperty("");
        public SimpleStringProperty address = new SimpleStringProperty("");
        public SimpleObjectProperty<LocalDate> hdate = new SimpleObjectProperty<LocalDate>();
        public SimpleObjectProperty<Departments> did = new SimpleObjectProperty();
        public SimpleObjectProperty<Project> pid = new SimpleObjectProperty<>();
        public SimpleStringProperty edu = new SimpleStringProperty("");
        public SimpleStringProperty sal = new SimpleStringProperty("");


        public void fromEmployee(Employee e) {
            this.id.set(e.getId());
            this.fname.set(e.getFirst_name());
            this.lname.set(e.getLast_name());
            this.address.set(e.getAddress());
            this.hdate.set(e.getHire_date());
            this.did.set(e.getDepartment());
            this.pid.set(e.getProject());
            this.edu.set(e.getEdu());
            this.sal.set(String.valueOf(e.getPayoff()));

        }

        public Employee toEmployee() {
            Employee e = new Employee();
            e.setId(this.id.getValue());
            e.setFirst_name(this.fname.getValue());
            e.setLast_name(this.lname.getValue());
            e.setAddress(this.address.getValue());
            e.setDepartment(this.did.getValue());
            e.setProject(this.pid.getValue());
            e.setHire_date(this.hdate.getValue());
            e.setEdu(this.edu.getValue());
            e.setPayoff(Integer.parseInt(this.sal.getValue()));
            return e;
        }
    }
}
