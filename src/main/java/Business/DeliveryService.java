package Business;

import Data.Writer;
import Data.FileReader;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * This class is basically the brain of the application. It contains all the information about the application (all the users, all the products, all the orders). DeliveryService extends Observable and implements IDeliveryServiceProcessing. This class is kept as the same instance throughout the running cycle of the application and at the end is serialized in a file. At each start of the app it is deserialized.
 */

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<MenuItem> allProducts = new ArrayList<>();

    private User currentUser;

    private int orderID = 0;
    public HashMap<Order, ArrayList<MenuItem>> orderMap = new HashMap<>();

    @Override
    public void importProducts() {
        FileReader fileReader = new FileReader();
        allProducts.addAll(fileReader.getProducts());
    }

    @Override
    public CompositeProduct createCompositeProduct(ArrayList<MenuItem> composition, String name) {
        CompositeProduct compositeProduct = new CompositeProduct();
        compositeProduct.setTitle(name);
        compositeProduct.setRating(0);
        int calories = 0, protein = 0, fat = 0, sodium = 0, price = 0;
        for (MenuItem m : composition) {
            calories += m.getCalories();
            protein += m.getProtein();
            fat += m.getFats();
            sodium += m.getSodium();
            price += m.getPrice();
        }
        compositeProduct.setCalories(calories);
        compositeProduct.setProtein(protein);
        compositeProduct.setFats(fat);
        compositeProduct.setSodium(sodium);
        compositeProduct.setPrice(price);

        notifyObservers();

        return compositeProduct;
    }

    @Override
    public Order createOrder(ArrayList<MenuItem> menuItems) throws IOException {
        orderID++;
        LocalDateTime date = LocalDateTime.now();
        Order newOrder = new Order(orderID, currentUser.getId(), date);
        int price = 0;
        for (MenuItem m : menuItems) {
            price += m.getPrice();
        }
        newOrder.setTotalPrice(price);
        orderMap.put(newOrder, menuItems);
        Writer writer = new Writer(newOrder, menuItems);
        writer.writeBill();
        setChanged();
        notifyObservers();
        return newOrder;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
