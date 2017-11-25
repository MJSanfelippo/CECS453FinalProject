package android.csulb.edu.cecs453finalproject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Michael on 11/11/2017.
 */

public class FoodItem {

    private String name;
    private double calories;
    private double protein;
    private double carbs;
    private double fats;
    private String date;

    public FoodItem(String name, double calories, String date){
        this.name = name;
        this.calories = calories;
        protein = carbs = fats = 0;
        this.date = date;
    }

    public FoodItem(String name, double calories, double protein, double carbs, double fats, String date) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public String getDate() { return date; }

    public void setDate(String date){ this.date = date; }
}
