package android.csulb.edu.cecs453finalproject;

import android.app.Activity;
import android.content.Intent;
import java.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by mjsan on 11/12/2017.
 */

public class AddNewFoodActivity extends AppCompatActivity {

    private EditText enterNameEditText;
    private EditText enterCaloriesEditText;
    private EditText enterCarbsEditText;
    private EditText enterFatsEditText;
    private EditText enterProteinEditText;

    private Button addButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food);

        addButton = (Button) findViewById(R.id.addButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        enterNameEditText = (EditText) findViewById(R.id.foodNameEditText);
        enterCaloriesEditText = (EditText) findViewById(R.id.enterCaloriesEditText);
        enterFatsEditText = (EditText) findViewById(R.id.enterFatsEditText);
        enterCarbsEditText = (EditText) findViewById(R.id.enterCarbsEditText);
        enterProteinEditText = (EditText) findViewById(R.id.enterProteinsEditText);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = "";
                double calories = 0;
                double carbs = 0;
                double fats = 0;
                double proteins = 0;

                boolean nameExists = false;
                boolean caloriesExists = false;

                if (!enterNameEditText.getText().toString().trim().equals("")){
                    name = enterNameEditText.getText().toString().trim();
                    nameExists = true;
                } else {
                    Toast.makeText(getApplicationContext(), "The food item needs a name", Toast.LENGTH_LONG).show();
                }
                if (!enterCaloriesEditText.getText().toString().trim().equals("")){
                    caloriesExists = true;
                    calories = Double.parseDouble(enterCaloriesEditText.getText().toString().trim());
                } else {
                    Toast.makeText(getApplicationContext(), "The food item needs an amount of calories", Toast.LENGTH_LONG).show();
                }
                if (!enterCarbsEditText.getText().toString().trim().equals("")){
                    carbs = Double.parseDouble(enterCarbsEditText.getText().toString().trim());
                }
                if (!enterFatsEditText.getText().toString().trim().equals("")){
                    fats = Double.parseDouble(enterFatsEditText.getText().toString().trim());
                }
                if (!enterProteinEditText.getText().toString().trim().equals("")){
                    proteins = Double.parseDouble(enterProteinEditText.getText().toString().trim());
                }
                if (nameExists && caloriesExists){
                    Intent intent = new Intent(AddNewFoodActivity.this, MainActivity.class);
                    Intent i = getIntent();
                    String date = i.getStringExtra("date");
                    FoodItem item = new FoodItem(name, calories, proteins, carbs, fats, date);
                    FoodDB db = new FoodDB(getApplicationContext());
                    db.insertNewFood(item);
                    db.close();
                    startActivity(intent);
                }

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewFoodActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
