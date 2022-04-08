package Business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * Order is a class used to create new product orders by the clients. It is represented by an ID (generated automatically), a client ID, the date is has been made and a total price. It is used together with an array list of menu items to form a hash map. The order is the key while the array list is the value.
 */

public class Order implements Serializable {

    private int orderID;
    private int clientID;
    private LocalDateTime date;
    private int totalPrice;

    public Order(int orderID, int clientID, LocalDateTime date) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.date = date;
    }

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID &&
                clientID == order.clientID &&
                totalPrice == order.totalPrice &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, clientID, date, totalPrice);
    }
}
