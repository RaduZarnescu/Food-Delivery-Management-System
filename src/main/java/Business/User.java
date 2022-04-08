package Business;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * A class used to store the users. It contains a username and a password with which each user logs in and an ID used to place orders. It also has a type field, used to differentiate the types of users (administrator, client and employee) and an array list of orders that client has made.
 */

public class User implements Serializable {

    private String username;
    private String password;
    private String type;
    private int id;
    private ArrayList<Order> orders = new ArrayList<>();

    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.type = "client";
    }

    public User(String username, String password, int id, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
