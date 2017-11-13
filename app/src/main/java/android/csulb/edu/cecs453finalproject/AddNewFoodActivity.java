package android.csulb.edu.cecs453finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                int calories;
                int carbs;
                int fats;
                int proteins;

                Bundle foodItem = new Bundle();
                boolean nameExists = false;
                boolean caloriesExists = false;

                if (!enterNameEditText.getText().toString().trim().equals("")){
                    name = enterNameEditText.getText().toString().trim();
                    nameExists = true;
                    foodItem.putString("name", name);
                } else {
                    Toast.makeText(getApplicationContext(), "The food item needs a name", Toast.LENGTH_LONG).show();
                }
                if (!enterCaloriesEditText.getText().toString().trim().equals("")){
                    caloriesExists = true;
                    calories = Integer.parseInt(enterCaloriesEditText.getText().toString().trim());
                    foodItem.putInt("calories", calories);
                } else {
                    Toast.makeText(getApplicationContext(), "The food item needs an amount of calories", Toast.LENGTH_LONG).show();
                }
                if (!enterCarbsEditText.getText().toString().trim().equals("")){
                    carbs = Integer.parseInt(enterCarbsEditText.getText().toString().trim());
                    foodItem.putInt("carbs", carbs);
                }
                if (!enterFatsEditText.getText().toString().trim().equals("")){
                    fats = Integer.parseInt(enterFatsEditText.getText().toString().trim());
                    foodItem.putInt("fats", fats);
                }
                if (!enterProteinEditText.getText().toString().trim().equals("")){
                    proteins = Integer.parseInt(enterProteinEditText.getText().toString().trim());
                    foodItem.putInt("proteins", proteins);
                }
                if (nameExists && caloriesExists){
                    Intent intent = new Intent(AddNewFoodActivity.this, MainActivity.class);
                    intent.putExtras(foodItem);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
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
