package Presentation;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * The main class extend the Application class and is used to start and shape the User Interface.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("a4login.fxml")));
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root, 694, 440));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

//        Business.User admin1 = new Business.User("admin1", "admin1", 1,"admin");
//        Business.User user1 = new Business.User("user1", "user1", 2);
//        Business.User user2 = new Business.User("user2", "user2", 3);
//        Business.User employee1 = new Business.User("emp1", "emp1", 4, "employee");
//        ArrayList<Business.User>users = new ArrayList<>();
//        users.add(admin1);
//        users.add(user1);
//        users.add(user2);
//        users.add(employee1);
//
//        Data.Serializer.serialize(users, "users.ser");



//        ArrayList<Business.User>users = Data.Serializer.deserializeUsers();
//        Business.DeliveryService deliveryService = new Business.DeliveryService();
//        deliveryService.users = users;
//        Data.Serializer.serialize(deliveryService, "service.ser");

    }
}