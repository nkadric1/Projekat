package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainFX extends Application {
    public void start(Stage primarystage) throws Exception {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/FXML/project.fxml"));
            Controller controller = new Controller();
            fl.setController(controller);
            Parent root = fl.load();
            primarystage.setTitle("Administrator login");
            primarystage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
         //   primarystage.setResizable(false); //iskljucivo se stavlja za dijaloske prozore
           primarystage.setMinHeight(150);
            primarystage.setMinWidth(250);

            primarystage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args){
    launch(args);}
}
