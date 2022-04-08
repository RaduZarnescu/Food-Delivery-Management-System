package Presentation;

import Business.DeliveryService;
import Business.User;
import Data.Serializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * Subclass of Controller; it handles the operations from the register window.
 */

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    @FXML private Button registerBtn;

    DeliveryService deliveryService;

    @FXML
    public void initialize(){
        //deliveryService = Data.Serializer.deserializeService();
        deliveryService = Controller.deliveryService;
    }

    public void changeSceneOnButtonAction(javafx.event.ActionEvent event, String fxml, Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fxml)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void register(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = new User(username, password, deliveryService.users.size() + 1);
        deliveryService.users.add(user);

        Serializer.serialize(deliveryService, "service.ser");

        changeSceneOnButtonAction(event, "a4login.fxml", registerBtn);
    }
}
