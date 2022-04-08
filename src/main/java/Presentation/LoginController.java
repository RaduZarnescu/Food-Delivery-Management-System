package Presentation;

import Business.DeliveryService;
import Business.User;
import Data.Serializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * Subclass of Controller; it handles the operations from the log in window.
 */

public class LoginController extends Controller{

    private @FXML Button loginBtn;
    private @FXML Button registerBtn;

    private @FXML TextField usernameField;
    private @FXML TextField passwordField;


    User currentUser;
    ArrayList<User>allUsers = new ArrayList<>();
    DeliveryService deliveryService;

    @FXML
    public void initialize(){
        //deliveryService = Data.Serializer.deserializeService();
        Controller.setDeliveryService(Serializer.deserializeService());
        Controller.deliveryService.addObserver(new EmployeeController());
        deliveryService = Controller.getDeliveryService();
    }

    public void changeSceneOnButtonAction(ActionEvent event, String fxml, Button btn) throws IOException {

        Stage stage = (Stage) btn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fxml)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent event) throws IOException {


        //allUsers = Data.Serializer.deserializeUsers();
        allUsers = deliveryService.users;

        String username = usernameField.getText();
        String password = passwordField.getText();

        for(User u: allUsers){
            if(u.getUsername().equals(username)){
                if(u.getPassword().equals(password)) {
                    if (u.getType().equals("admin")) {
                        currentUser = u;
                        deliveryService.setCurrentUser(u);
                        changeSceneOnButtonAction(event, "a4adminPage.fxml", loginBtn);
                        Serializer.serialize(allUsers, "users.ser");
                        Serializer.serialize(deliveryService, "service.ser");
                        return;
                    }
                    else if(u.getType().equals("client")){
                        currentUser = u;
                        deliveryService.setCurrentUser(u);
                        changeSceneOnButtonAction(event, "a4client.fxml", loginBtn);
                        Serializer.serialize(allUsers, "users.ser");
                        Serializer.serialize(deliveryService, "service.ser");
                        return;
                    }
                    else if(u.getType().equals("employee")){
                        currentUser = u;
                        deliveryService.setCurrentUser(u);
                        changeSceneOnButtonAction(event, "a4employee.fxml", loginBtn);
                        Serializer.serialize(allUsers, "users.ser");
                        Serializer.serialize(deliveryService, "service.ser");
                        return;
                    }
                }
            }
        }
        System.out.println("Business.User not found");

    }

    public void register(ActionEvent event) throws IOException {
        changeSceneOnButtonAction(event, "a4register.fxml", registerBtn);
    }
}
