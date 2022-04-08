package Business;

import java.io.Serializable;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * This class is a derived class of MenuItem. It represents a new product formed from other instances of MenuItem, be it base or composite products.
 */

public class CompositeProduct extends MenuItem implements Serializable {
    public CompositeProduct(String title, double rating, int calories, int protein, int fats, int sodium, int price) {
        super(title, rating, calories, protein, fats, sodium, price);
    }

    public CompositeProduct() {
    }
}
