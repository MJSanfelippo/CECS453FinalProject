package android.csulb.edu.cecs453finalproject;

/**
 * Created by Michael on 11/11/2017.
 */

public class FoodItem {

    private String name;
    private int calories;
    private int protein;
    private int carbs;
    private int fats;

    public FoodItem(String name, int calories){
        this.name = name;
        this.calories = calories;
        protein = carbs = fats = 0;
    }

    public FoodItem(String name, int calories, int protein, int carbs, int fats) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }
}
