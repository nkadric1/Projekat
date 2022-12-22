package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller {
    public TextField fieldUser;
    public PasswordField fieldPassword;
    public Button bBtn;

    @FXML
    public void initialize() {

        fieldUser.getStyleClass().add("fieldisnotOK");
        fieldUser.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (fieldUser.getText().trim().isEmpty()) {
                    fieldUser.getStyleClass().removeAll("fieldisOK");
                    fieldUser.getStyleClass().add("fieldisnotOK");
                } else {
                    fieldUser.getStyleClass().removeAll("fieldisnotOK");
                    fieldUser.getStyleClass().add("fieldisOK");
                }
            }
        });
    }

    public void Click(ActionEvent actionEvent) throws IOException {
        //if usernamefield is empty then show an error
        if (fieldUser.getText().isEmpty()) {
            return;
        }
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/new.fxml"));
        PageController p = new PageController((EmployeeDAOSQLImpl) DaoFactory.employeeDao());
        fxmlLoader.setController(p);
        Parent root = fxmlLoader.load();
         stage.setTitle("Welcome, search it!");
         stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
         stage.show();
}
//kako pozvati funkciju za dodavanje novog zap
//public void addnew(ActionEvent actionEvent) throws IOException{
// }
}