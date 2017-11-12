package android.csulb.edu.cecs453finalproject;



/**
 * Created by Michael on 11/11/2017.
 */

public class Formulas {

    public static int convertToDp(int desiredDp, float scale){
        return (int) (desiredDp*scale + 0.5f);
    }

    /**
     *
     * @param weight the weight in kg
     * @param height the height in cm
     * @param age the age in years
     * @return
     */
    public static int harrisBenedictEquation(double weight, int height, int age, boolean isMale){
        int calories;
        if (isMale){
            calories = (int) (66.4730 + (13.7516 * weight) + (5.0033 * height) - (6.6550 * age));
        } else {
            calories = (int) (655.0955 + (9.5634 * weight) + (1.8496 * height) - (4.6756 * age));
        }
        return calories;
    }

    public static double convertLbToKg(float weightInLb){
        return weightInLb / 2.2046226218;
    }

    public static int convertToCm(int feet, int inches){
        int totalInches = (feet*12) + inches;
        return (int) (totalInches * 2.54);
    }

}
