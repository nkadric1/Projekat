package ba.unsa.etf.rpr;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller {
    public TextField fieldUser;
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
 /*  FXMLLoader fl = new FXMLLoader(getClass().getResource("/FXML/new.fxml"));


    Parent root = fl.load();
       // NewWindow novi=fl.getController();
      //  novi.labela.setText(novi.labela.getText()+fieldUser.getText());
        stage.setTitle("Query");
     stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
   // zasto ovdje ne mozemo na ovaj nacin postaviti novu scenu nego moramona Vedranov

*/

    Parent root=FXMLLoader.load((getClass().getResource("/FXML/new.fxml")));
     stage.setTitle("Query");
     stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
     stage.show();
}}