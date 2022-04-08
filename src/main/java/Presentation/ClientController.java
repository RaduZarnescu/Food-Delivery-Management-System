package Presentation;

import Business.*;

import Business.MenuItem;
import Data.Serializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * Subclass of Controller; it handles all the operations a client performs using the dedicated client user interface.
 */

public class ClientController extends Controller{

    @FXML private TableView<MenuItem> menuTable = new TableView<>();
    @FXML private TableColumn<MenuItem, String> titleCol;
    @FXML private TableColumn<MenuItem, Double> ratingCol;
    @FXML private TableColumn<MenuItem, Integer> caloriesCol;
    @FXML private TableColumn<MenuItem, Integer> proteinCol;
    @FXML private TableColumn<MenuItem, Integer> fatCol;
    @FXML private TableColumn<MenuItem, Integer> sodiumCol;
    @FXML private TableColumn<MenuItem, Integer> priceCol;

    @FXML private TextField titleFilter;
    @FXML private TextField ratingFilter;
    @FXML private TextField caloriesFilter;
    @FXML private TextField proteinFilter;
    @FXML private TextField fatFilter;
    @FXML private TextField sodiumFilter;
    @FXML private TextField priceFilter;

    @FXML private Button searchBtn;
    @FXML private Button backBtn;
    @FXML private Label label1;

    ObservableList<MenuItem> allProducts = FXCollections.observableArrayList();

    DeliveryService deliveryService;

    private int ratingOption = -1;
    private int caloriesOption = -1;
    private int proteinOption = -1;
    private int fatOption = -1;
    private int sodiumOption = -1;
    private int priceOption = -1;

    private ArrayList<MenuItem>orderItems = new ArrayList<>();
    private int productsAdded;

    @FXML
    public void initialize(){
        menuTable.setRowFactory(tv -> {
            TableRow<MenuItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    MenuItem clickedRow = row.getItem();
                }
            });
            return row ;
        });

        deliveryService = Controller.deliveryService;
        allProducts.addAll(deliveryService.allProducts);
        viewMenu();
    }

    public void viewMenu(){

        titleCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("title"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("rating"));
        caloriesCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("calories"));
        proteinCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("protein"));
        fatCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("fats"));
        sodiumCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("sodium"));
        priceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("price"));

        menuTable.getItems().clear();
        menuTable.getItems().addAll(allProducts);
    }

    public void item1OnAction(){
        ratingOption = 1;
    }
    public void item2OnAction(){
        ratingOption = 0;
    }
    public void item3OnAction(){
        caloriesOption = 1;
    }
    public void item4OnAction(){
        caloriesOption = 0;
    }
    public void item5OnAction(){
        proteinOption = 1;
    }
    public void item6OnAction(){
        proteinOption = 0;
    }
    public void item7OnAction(){
        fatOption = 1;
    }
    public void item8OnAction(){
        fatOption = 0;
    }
    public void item9OnAction(){
        sodiumOption = 1;
    }
    public void item10OnAction(){
        fatOption = 0;
    }
    public void item11OnAction(){
        priceOption = 1;
    }
    public void item12OnAction(){
        priceOption = 0;
    }

    public void filter(){

        ArrayList<MenuItem> filteredItems = new ArrayList<>();
        filteredItems.addAll(allProducts);

        if(!titleFilter.getText().equals("")){
            filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                    .filter(p -> p.getTitle().contains(titleFilter.getText()))
                    .collect(Collectors.toList());
        }
        if(!ratingFilter.getText().equals("")){
            if(ratingOption == 1){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getRating() >= Double.parseDouble(ratingFilter.getText()))
                        .collect(Collectors.toList());
            }
            else if(ratingOption == 0){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getRating() <= Double.parseDouble(ratingFilter.getText()))
                        .collect(Collectors.toList());
            }
        }
        if(!caloriesFilter.getText().equals("")){
            if(caloriesOption == 1){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getCalories() >= Integer.parseInt(caloriesFilter.getText()))
                        .collect(Collectors.toList());
            }
            else if(caloriesOption == 0){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getCalories() <= Integer.parseInt(caloriesFilter.getText()))
                        .collect(Collectors.toList());
            }
        }
        if(!proteinFilter.getText().equals("")){
            if(proteinOption == 1){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getProtein() >= Integer.parseInt(proteinFilter.getText()))
                        .collect(Collectors.toList());
            }
            else if(proteinOption == 0){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getProtein() <= Integer.parseInt(proteinFilter.getText()))
                        .collect(Collectors.toList());
            }
        }
        if(!fatFilter.getText().equals("")){
            if(fatOption == 1){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getFats() >= Integer.parseInt(fatFilter.getText()))
                        .collect(Collectors.toList());
            }
            else if(fatOption == 0){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getFats() <= Integer.parseInt(fatFilter.getText()))
                        .collect(Collectors.toList());
            }
        }
        if(!sodiumFilter.getText().equals("")){
            if(sodiumOption == 1){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getSodium() >= Integer.parseInt(sodiumFilter.getText()))
                        .collect(Collectors.toList());
            }
            else if(sodiumOption == 0){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getSodium() <= Integer.parseInt(sodiumFilter.getText()))
                        .collect(Collectors.toList());
            }
        }
        if(!priceFilter.getText().equals("")){
            if(priceOption == 1){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getPrice() >= Integer.parseInt(priceFilter.getText()))
                        .collect(Collectors.toList());
            }
            else if(priceOption == 0){
                filteredItems = (ArrayList<MenuItem>) filteredItems.stream()
                        .filter(p -> p.getPrice() <= Integer.parseInt(priceFilter.getText()))
                        .collect(Collectors.toList());
            }
        }
        menuTable.getItems().clear();
        menuTable.getItems().addAll(filteredItems);
    }

    public void addToOrder(ActionEvent event){
        MenuItem clickedItem =  menuTable.getSelectionModel().selectedItemProperty().get();
        clickedItem.timesOrdered++;
        orderItems.add(clickedItem);
        productsAdded++;
        label1.setText(clickedItem.getTitle() + "added");
    }

    public void order(ActionEvent event) throws IOException {

        Order order = deliveryService.createOrder(orderItems);
        deliveryService.getCurrentUser().getOrders().add(order);

        Serializer.serialize(deliveryService, "service.ser");

        label1.setText("Order placed");
        orderItems.removeAll(orderItems);
    }

    public void changeSceneOnButtonAction(ActionEvent event, String fxml, Button btn) throws IOException {

        Stage stage = (Stage) btn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fxml)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void back(ActionEvent event) throws IOException {
        changeSceneOnButtonAction(event, "a4login.fxml", backBtn);
    }
}
