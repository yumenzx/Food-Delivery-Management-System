package business;

import java.io.Serializable;

/**
 * clasa principala pentru orice tip de produs
 */
public abstract class MenuItem implements Serializable {

    private final String title;
    private final double rating;
    private final int calories;
    private final int proteins;
    private final int fats;
    private final int sodium;
    private final int price;

    public MenuItem(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getSodium() {
        return sodium;
    }

    public int computePrice(){
        return price;
    }

}
