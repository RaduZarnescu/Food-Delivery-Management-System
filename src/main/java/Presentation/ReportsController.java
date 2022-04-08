package Presentation;

import Business.DeliveryService;
import Business.MenuItem;
import Business.Order;
import Business.User;
import Data.Writer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * This controller is used to generate the requested reports by the administrator.
 */

public class ReportsController extends Controller {

    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    @FXML private Button btn4;

    @FXML private TextField timeIntervalBegining;
    @FXML private TextField timeIntervalEnd;
    @FXML private TextField x;
    @FXML private TextField y;
    @FXML private TextField z;
    @FXML private TextField specifiedDay;

    DeliveryService deliveryService;
    Writer writer = new Writer();

    @FXML
    public void initialize(){
        deliveryService = Controller.deliveryService;
    }

    public void timeIntervalReport(ActionEvent event) throws IOException {

        HashMap<Order, ArrayList<MenuItem>>newMap = deliveryService.orderMap;

        int start = Integer.parseInt(timeIntervalBegining.getText());
        int end = Integer.parseInt(timeIntervalEnd.getText());

        List<Map.Entry<Order, ArrayList<MenuItem>>> set = newMap.entrySet().stream()
                .filter(p -> p.getKey().getDate().getHour() > start && p.getKey().getDate().getHour() < end)
                .collect(Collectors.toList());

        writer.timeIntervalReport(set, "time_interval_report.txt");

    }

    public void xReport(ActionEvent event) throws IOException {
        int nr = Integer.parseInt(x.getText());
        ArrayList<MenuItem> menu = deliveryService.allProducts;
        menu = (ArrayList<MenuItem>) menu.stream()
                .filter(p -> p.timesOrdered > nr)
                .collect(Collectors.toList());
        writer.orderedMoreThanReport(menu, nr);

    }

    public void yzReport(ActionEvent event) throws IOException {
        int nr1 = Integer.parseInt(y.getText());
        int nr2 = Integer.parseInt(z.getText());
        ArrayList<User> users = deliveryService.users;
        users = (ArrayList<User>) users.stream()
                .filter(p -> p.getOrders().size() > nr1 && p.getType().equals("client"))
                .collect(Collectors.toList());
        ArrayList<User> usersFinal = new ArrayList<>();
        for(User u: users){
            int count = 0;
            for(Order o: u.getOrders()){
                if(o.getTotalPrice() > nr2){
                    count++;
                }
            }
            if(count > nr1){
                usersFinal.add(u);
            }
        }
        writer.yzReport(usersFinal, nr1, nr2);
    }

    public void dayReport(ActionEvent event) throws IOException {
        ArrayList<MenuItem>itemsOrdered = new ArrayList<>();

        HashMap<Order, ArrayList<MenuItem>>newMap = deliveryService.orderMap;

        int day = Integer.parseInt(specifiedDay.getText());

        List<Map.Entry<Order, ArrayList<MenuItem>>> set = newMap.entrySet().stream()
                .filter(p -> p.getKey().getDate().getDayOfMonth() == day)
                .collect(Collectors.toList());

        for(Map.Entry<Order, ArrayList<MenuItem>> s: set){
            for(MenuItem m: s.getValue()){
                m.orderedInADay++;
            }
        }

        for(Map.Entry<Order, ArrayList<MenuItem>> s: set){
            itemsOrdered.addAll(s.getValue());
        }
        itemsOrdered = (ArrayList<MenuItem>) itemsOrdered.stream()
                .distinct()
                .collect(Collectors.toList());

//        for(Business.MenuItem m: allItems){
//            if(m.orderedInADay > 0){
//                itemsOrdered.add(m);
//            }
//        }
        writer.specifiedDayReport(itemsOrdered);

        for(MenuItem m: deliveryService.allProducts){
            m.orderedInADay = 0;
        }

    }
}
