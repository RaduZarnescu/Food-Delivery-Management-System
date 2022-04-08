package Data;

import Business.MenuItem;
import Business.Order;
import Business.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * This class is used to write information to .txt files. It generates bills for each order and also generates the required reports.
 */

public class Writer {
    public FileWriter file;
    public Order order;
    public ArrayList<MenuItem> menuItems;

    public void writeBill(){
        int price = 0;
        try {
            file.write("Business.Order " + order.getOrderID() + ":\n");
            for(MenuItem m: menuItems){
                file.write(m.getTitle() + ", price: " + m.getPrice() + "\n");
                price += m.getPrice();
            }
            file.write("Total: " + price + "\n\n");
            file.write(order.getDate().toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void timeIntervalReport(List<Map.Entry<Order, ArrayList<MenuItem>>> set, String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (Map.Entry<Order, ArrayList<MenuItem>> orderArrayListEntry : set) {
            fileWriter.write("Order " + orderArrayListEntry.getKey().getOrderID() + ", client " + orderArrayListEntry.getKey().getClientID() + ", time " + orderArrayListEntry.getKey().getDate().getHour() + "\n");
        }
        fileWriter.close();
    }

    public void orderedMoreThanReport(ArrayList<MenuItem> menuItems, int x) throws IOException {
        FileWriter writer = new FileWriter("products_ordered_more_than_report.txt");
        writer.write("Products ordered more than " + x + " times\n\n");
        for(MenuItem m: menuItems){
            writer.write(m.getTitle() + ": ordered " + m.timesOrdered + " times\n");
        }
        writer.close();
    }

    public void yzReport(ArrayList<User> users, int y, int z) throws IOException {
        FileWriter writer = new FileWriter("report3.txt");
        writer.write("Clients who ordered " + y + " times with a price higher than " + z + "\n\n");
        for(User u: users){
            writer.write(u.getUsername() + "\n");
        }
        writer.close();
    }

    public void specifiedDayReport(ArrayList<MenuItem> items) throws IOException {
        FileWriter writer = new FileWriter("report4.txt");
        for(MenuItem m: items){
            writer.write(m.getTitle() + " was ordered " + m.orderedInADay + " times\n");
        }
        writer.close();
    }

    public Writer(Order order, ArrayList<MenuItem> menuItems) throws IOException {
        this.order = order;
        this.menuItems = menuItems;
        this.file = new FileWriter("order" + order.getOrderID() + "bill.txt");
    }

    public Writer() {
    }
}
