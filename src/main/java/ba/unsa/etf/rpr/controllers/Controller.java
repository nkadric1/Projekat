package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.UsersDAOSQLImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import java.io.IOException;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller {
    public TextField fieldUser;
    public PasswordField passfield;

    private void openDialog(String title, String file, Object controller) {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource(file));
            l.setController(controller);
            Stage s = new Stage();
            Scene scene = new Scene(l.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            scene.setFill(Color.TRANSPARENT);
            s.initStyle(StageStyle.TRANSPARENT);
            s.setScene(scene);
            s.setTitle(title);
            s.setResizable(false);
            s.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void Click(ActionEvent actionEvent) throws IOException {
        if (fieldUser.getText().isEmpty()) {
            new Alert(Alert.AlertType.NONE, "Please, enter username!", ButtonType.OK).show();
            return;
        } else if (passfield.getText().isEmpty()) {
            new Alert(Alert.AlertType.NONE, "Please, enter password!", ButtonType.OK).show();
            return;
        }
        String us = fieldUser.getText();
        String pas = passfield.getText();
        UsersDAOSQLImpl u = new UsersDAOSQLImpl();
        boolean flag = u.validate(us, pas);
        if (!flag) {
            new Alert(Alert.AlertType.NONE, "Please, enter correct username and password!", ButtonType.OK).show();
            fieldUser.clear();
            passfield.clear();
        } else {
            openDialog("Company report!", "/FXML/new.fxml", new PageController());
        }
    }

}