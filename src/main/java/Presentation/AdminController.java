package Presentation;

import Business.CompositeProduct;
import Business.DeliveryService;
import Data.Serializer;
import Business.BaseProduct;
import Business.MenuItem;
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

/**
 * @author Radu Zarnescu
 * @version 1.0
 * Subclass of Controller; it handles all the operations an administrator performs using the dedicated admin user interface.
 */

public class AdminController extends Controller{

    @FXML private TableView<MenuItem> menuTable = new TableView<>();
    @FXML private TableColumn<MenuItem, String> titleCol;
    @FXML private TableColumn<MenuItem, Double> ratingCol;
    @FXML private TableColumn<MenuItem, Integer> caloriesCol;
    @FXML private TableColumn<MenuItem, Integer> proteinCol;
    @FXML private TableColumn<MenuItem, Integer> fatCol;
    @FXML private TableColumn<MenuItem, Integer> sodiumCol;
    @FXML private TableColumn<MenuItem, Integer> priceCol;

    @FXML private Button addBtn;
    @FXML private Button createBtn;

    @FXML private TextField compNameField;
    @FXML private Label label1;

    @FXML private TextField titleField;
    @FXML private TextField ratingField;
    @FXML private TextField caloriesField;
    @FXML private TextField proteinField;
    @FXML private TextField fatField;
    @FXML private TextField sodiumField;
    @FXML private TextField priceField;
    @FXML private Button addNewProdBtn;
    @FXML private Button deleteBtn;
    @FXML private Button modifyBtn;
    @FXML private Button backBtn;
    @FXML private Button reportsBtn;


    public ObservableList<MenuItem> allProducts = FXCollections.observableArrayList();

    public ArrayList<MenuItem> composition = new ArrayList<>();

    public DeliveryService deliveryService;

    int productsAdded;

    @FXML
    public void initialize(){

        //allProducts = null;
        deliveryService = Controller.getDeliveryService();

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

        //deliveryService = Data.Serializer.deserializeService();
        allProducts.addAll(deliveryService.allProducts);

        //ArrayList<Business.BaseProduct> baseProducts = deliveryService.importProducts();
        //deliveryService.allProducts.addAll(baseProducts);
        //allProducts.addAll(deliveryService.allProducts);

        viewMenu();
    }

    public void importProd(ActionEvent event){
        deliveryService.importProducts();
        allProducts.addAll(deliveryService.allProducts);
        Serializer.serialize(deliveryService, "service.ser");
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

    public void addToComposite(ActionEvent event){
        MenuItem clickedItem =  menuTable.getSelectionModel().selectedItemProperty().get();
        composition.add(clickedItem);
        productsAdded++;
        label1.setText(clickedItem.getTitle() + "added");
    }

    public void createCompositeProduct(ActionEvent event){

        String compName = compNameField.getText();
        CompositeProduct newProd = deliveryService.createCompositeProduct(composition, compName);

        allProducts.addAll(newProd);

        deliveryService.allProducts.add(newProd);
        Serializer.serialize(deliveryService, "service.ser");

        viewMenu();

        productsAdded = 0;
        label1.setText("Composite Product added");
    }

    public void addNewProd(ActionEvent event){
        String newTitle = titleField.getText();
        Double newRating = Double.parseDouble(ratingField.getText());
        int newCalories = Integer.parseInt(caloriesField.getText());
        int newProtein = Integer.parseInt(proteinField.getText());
        int newFat = Integer.parseInt(fatField.getText());
        int newSodium = Integer.parseInt(sodiumField.getText());
        int newPrice = Integer.parseInt(priceField.getText());

        BaseProduct newProd = new BaseProduct(newTitle, newRating, newCalories, newProtein, newFat, newSodium, newPrice);

        allProducts.add(newProd);
        deliveryService.allProducts.add(newProd);

        viewMenu();

        Serializer.serialize(deliveryService, "service.ser");
    }

    public void deleteProd(ActionEvent event){
        MenuItem clickedItem =  menuTable.getSelectionModel().selectedItemProperty().get();
        allProducts.remove(clickedItem);
        deliveryService.allProducts.remove(clickedItem);
        viewMenu();
        Serializer.serialize(deliveryService, "service.ser");
    }

    public void modify(ActionEvent event){
        MenuItem clickedItem =  menuTable.getSelectionModel().selectedItemProperty().get();
        for(MenuItem m: allProducts){
            if(m.getTitle().equals(clickedItem.getTitle())){
                if(!titleField.getText().equals("")){
                    m.setTitle(titleField.getText());
                }
                if(!ratingField.getText().equals("")){
                    m.setRating(Double.parseDouble(ratingField.getText()));
                }
                if(!caloriesField.getText().equals("")){
                    m.setCalories(Integer.parseInt(caloriesField.getText()));
                }
                if(!proteinField.getText().equals("")){
                    m.setProtein(Integer.parseInt(proteinField.getText()));
                }
                if(!fatField.getText().equals("")){
                    m.setFats(Integer.parseInt(fatField.getText()));
                }
                if(!sodiumField.getText().equals("")){
                    m.setSodium(Integer.parseInt(sodiumField.getText()));
                }
                if(!priceField.getText().equals("")){
                    m.setPrice(Integer.parseInt(priceField.getText()));
                }
                break;
            }

        }
        for(MenuItem m: deliveryService.allProducts) {
            if (m.getTitle().equals(clickedItem.getTitle())) {
                if (!titleField.getText().equals("")) {
                    m.setTitle(titleField.getText());
                }
                if (!ratingField.getText().equals("")) {
                    m.setRating(Double.parseDouble(ratingField.getText()));
                }
                if (!caloriesField.getText().equals("")) {
                    m.setCalories(Integer.parseInt(caloriesField.getText()));
                }
                if (!proteinField.getText().equals("")) {
                    m.setProtein(Integer.parseInt(proteinField.getText()));
                }
                if (!fatField.getText().equals("")) {
                    m.setFats(Integer.parseInt(fatField.getText()));
                }
                if (!sodiumField.getText().equals("")) {
                    m.setSodium(Integer.parseInt(sodiumField.getText()));
                }
                if (!priceField.getText().equals("")) {
                    m.setPrice(Integer.parseInt(priceField.getText()));
                }
                break;
            }
        }
        viewMenu();
        Serializer.serialize(deliveryService, "service.ser");
    }

    public void changeSceneOnButtonAction(ActionEvent event, String fxml, Button btn) throws IOException {

        Stage stage = (Stage) btn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fxml)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void reports(ActionEvent event) throws IOException {
        changeSceneOnButtonAction(event, "a4reports.fxml", reportsBtn);
    }

    public void back(ActionEvent event) throws  IOException{
        changeSceneOnButtonAction(event, "a4login.fxml", backBtn);
    }

}
