package ba.unsa.etf.rpr;

import java.awt.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
public class Controller {
    public TextField fieldUser;
@FXML
public void initialize(){
    fieldUser.getStyleClass().add("fieldisnotOK");
    fieldUser.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            if(fieldUser.getText().trim().isEmpty()){
                fieldUser.getStyleClass().removeAll("fieldisOK");
                fieldUser.getStyleClass().add("fieldisnotOK");
            }
            else {
                fieldUser.getStyleClass().removeAll("fieldisnotOK");
                fieldUser.getStyleClass().add("fieldisOK");
            }
        }
    });
}
 public void Click(ActionEvent actionEvent){
     //if usernamefield is empty then show an error
     if(fieldUser.getText().isEmpty()){
         fieldUser.getStyleClass().add("fieldisnotOK");
        /* Alert alert = new Alert(Alert.
        AlertType.ERROR);
         alert.setTitle("Error!");
         alert.setHeaderText("Username is not recognized");
         alert.setContentText("You must enter username to get into the database!");

         alert.showAndWait();*/
         return;
     }
 Alert x=new Alert(Alert.AlertType.INFORMATION);
 x.setTitle("Hello");
 x.setHeaderText("Hi");
 x.setContentText("Username is: "+fieldUser.getText());
 x.show();
     System.out.println("Dialog closed");
 }
}
