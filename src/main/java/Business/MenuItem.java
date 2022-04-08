package Business;

import java.io.Serializable;

/**
 * @author Radu Zarnescu
 * @version 1.0
 * The MenuItem class represents all the products in the menu, including base products, as well as composite products. It contains 7 fields: title, rating, calories, protein, fat, sodium and price.
 */

public class MenuItem implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fats;
    private int sodium;
    private int price;

    public int timesOrdered = 0;
    public int orderedInADay = 0;

    public MenuItem(String title, double rating, int calories, int protein, int fats, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
    }

    public MenuItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
