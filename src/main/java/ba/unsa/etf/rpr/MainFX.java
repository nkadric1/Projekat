package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainFX extends Application {
    public GridPane grid=new GridPane();
    public void start(Stage primarystage) throws Exception {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/FXML/first.fxml"));
            Controller controller = new Controller();
            fl.setController(controller);
            Parent root = fl.load();
            primarystage.setTitle("DeveLoop");
            Scene scene=new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
//            grid.setBackground(Background.fill(Color.AZURE));
            primarystage.setScene(scene);
            primarystage.setResizable(false); //iskljucivo se stavlja za dijaloske prozore
            primarystage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args){
    launch(args);
    }
}
