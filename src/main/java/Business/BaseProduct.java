package Business;

import java.io.Serializable;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * BaseProduct is a subclass of MenuItem. It represents the products imported from the csv file.
 */

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String title, double rating, int calories, int protein, int fats, int sodium, int price) {
        super(title, rating, calories, protein, fats, sodium, price);
    }

    public BaseProduct() {
    }
}
