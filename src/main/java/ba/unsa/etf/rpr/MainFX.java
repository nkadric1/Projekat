package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * @author Kadric Nerma
 * Main class for working with JAVAFX framework
 */
public class MainFX extends Application {
    public void start(Stage primarystage) throws Exception {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/FXML/first.fxml"));
            Controller controller = new Controller();
            fl.setController(controller);
            Parent root = fl.load();
            primarystage.setTitle("DeveLoop");
            Scene scene=new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            primarystage.setScene(scene);
            primarystage.setResizable(false);
            primarystage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args){
    launch(args);
    }
}
