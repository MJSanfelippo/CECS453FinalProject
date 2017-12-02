package android.csulb.edu.cecs453finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nam on 12/1/2017.
 */

public class EditFoodInfoActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText caloriesEditText;
    private EditText carbsEditText;
    private EditText proteinsEditText;
    private EditText fatsEditText;

    Button editButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        editButton = (Button) findViewById(R.id.editButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        caloriesEditText = (EditText) findViewById(R.id.caloriesEditText);
        fatsEditText = (EditText) findViewById(R.id.fatsEditText);
        carbsEditText = (EditText) findViewById(R.id.carbsEditText);
        proteinsEditText = (EditText) findViewById(R.id.proteinsEditText);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        final FoodItem foodItem = (FoodItem)b.getSerializable("FoodItem");

        String currentName = foodItem.getName();
        double currentCalories = foodItem.getCalories();
        double currentCarbs = foodItem.getCarbs();
        double currentProteins = foodItem.getProtein();
        double currentFats = foodItem.getFats();

        nameEditText.setText(currentName);
        caloriesEditText.setText(currentCalories+"");
        carbsEditText.setText(currentCarbs+"");
        proteinsEditText.setText(currentProteins+"");
        fatsEditText.setText(currentFats+"");

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = "";
                double calories = 0;
                double carbs = 0;
                double fats = 0;
                double proteins = 0;

                boolean nameExists = false;
                boolean caloriesExists = false;

                if (!nameEditText.getText().toString().trim().equals("")){
                    name = nameEditText.getText().toString().trim();
                    nameExists = true;
                } else {
                    Toast.makeText(getApplicationContext(), "The food item needs a name", Toast.LENGTH_LONG).show();
                }
                if (!caloriesEditText.getText().toString().trim().equals("")){
                    caloriesExists = true;
                    calories = Double.parseDouble(caloriesEditText.getText().toString().trim());
                } else {
                    Toast.makeText(getApplicationContext(), "The food item needs an amount of calories", Toast.LENGTH_LONG).show();
                }
                if (!carbsEditText.getText().toString().trim().equals("")){
                    carbs = Double.parseDouble(carbsEditText.getText().toString().trim());
                }
                if (!fatsEditText.getText().toString().trim().equals("")){
                    fats = Double.parseDouble(fatsEditText.getText().toString().trim());
                }
                if (!proteinsEditText.getText().toString().trim().equals("")){
                    proteins = Double.parseDouble(proteinsEditText.getText().toString().trim());
                }
                if (nameExists && caloriesExists){
                    Intent intent = new Intent(EditFoodInfoActivity.this, MainActivity.class);
                    Intent i = getIntent();
                    String date = i.getStringExtra("date");
                    FoodItem item = foodItem;
                    item.setName(name);
                    item.setCalories((int) calories);
                    item.setCarbs((int) carbs);
                    item.setFats((int) fats);
                    item.setProtein((int) proteins);
                    FoodDB db = new FoodDB(getApplicationContext());
                    db.updateFood(item);
                    db.close();
                    startActivity(intent);
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditFoodInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
