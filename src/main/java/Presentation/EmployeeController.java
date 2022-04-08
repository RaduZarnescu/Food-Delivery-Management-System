package Presentation;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * This is the class used to implement the observer. All it does is to print a message when a new order is made.
 */

public class EmployeeController implements Observer {

    @FXML TextArea textArea = new TextArea();

    @Override
    public void update(Observable o, Object arg) {
        textArea.appendText("A new order has been made.\n");
    }
}
