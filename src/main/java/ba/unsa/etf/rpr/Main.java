package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage pstage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("project.fxml"));
        pstage.setTitle("Login");
        pstage.setScene(new Scene(root,300,275));
        pstage.show();
        Stage s=new Stage();
        s.setTitle("Next stage");
        s.setScene(new Scene(new Button("NEXT"),100,100));
        s.show();
    }
    public static  void main(String []args){
        launch(args);
    }
}
