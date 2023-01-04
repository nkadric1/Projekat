package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.EmployeeDAOSQLImpl;
import ba.unsa.etf.rpr.dao.UsersDAOSQLImpl;
import ba.unsa.etf.rpr.dao.UsersDao;
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
    public PasswordField passfield;
    public Button bBtn;

    @FXML
    public void initialize() {

     /*   fieldUser.getStyleClass().add("fieldisnotOK");
        fieldUser.textProperty().addListener(new ChangeListener<String>() {
            @Override
           public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (fieldUser.getText().trim().isEmpty() || passfield.getText().trim().isEmpty()) {
                    fieldUser.getStyleClass().removeAll("fieldisOK");
                    fieldUser.getStyleClass().add("fieldisnotOK");
                    passfield.getStyleClass().removeAll("fieldisOK");
                    passfield.getStyleClass().add("fieldisnotOK");
                } else {
                    fieldUser.getStyleClass().removeAll("fieldisnotOK");
                    fieldUser.getStyleClass().add("fieldisOK");
                    passfield.getStyleClass().removeAll("fieldisnotOK");
                    passfield.getStyleClass().add("fieldisOK");
                }
            }
        });*/
    }
//kako popraviti ove boje da odmah oba textfielda budu crveni i da pozelene kada je sve tacno

    public void Click(ActionEvent actionEvent) throws IOException {
        //if usernamefield is empty then show an error
       if (fieldUser.getText().isEmpty() ) {
           infobox("Please, enter username!", null, "Field is empty.");
       }
       else if ( passfield.getText().isEmpty()){
           infobox("Please, enter password!", null, "Field is empty.");
       }
String us=fieldUser.getText();
        String pas=passfield.getText();
   UsersDAOSQLImpl u=new UsersDAOSQLImpl();
        boolean flag=u.validate(us,pas);
        if (!flag) {
            infobox("Please, enter correct username and password!", null, "Failed input!");
            fieldUser.clear();
            passfield.clear();
        }else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/new.fxml"));
            PageController p = new PageController((EmployeeDAOSQLImpl) DaoFactory.employeeDao());
            fxmlLoader.setController(p);
            Parent root = fxmlLoader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle("Company report!");
            stage.show();
        }

}
public static void infobox(String info,String header, String title){
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setContentText(info);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.showAndWait();

}

}