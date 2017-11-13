package android.csulb.edu.cecs453finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mjsan on 11/12/2017.
 */

public class DisplayFoodInfoActivity extends AppCompatActivity {

    private TextView name;
    private TextView calories;
    private TextView carbs;
    private TextView proteins;
    private TextView fats;

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_food_info);

        name = (TextView) findViewById(R.id.realNameTextView);
        calories = (TextView) findViewById(R.id.realCaloriesTextView);
        carbs = (TextView) findViewById(R.id.realCarbsTextView);
        proteins = (TextView) findViewById(R.id.realProteinsTextView);
        fats = (TextView) findViewById(R.id.realFatsTextView);

        returnButton = (Button) findViewById(R.id.backToMainPageButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String realName = b.getString("name");
        int realCalories = b.getInt("calories");
        int realCarbs = b.getInt("carbs");
        int realProteins = b.getInt("proteins");
        int realFats = b.getInt("fats");

        name.setText(realName);
        calories.setText(realCalories+"");
        carbs.setText(realCarbs+"");
        proteins.setText(realProteins+"");
        fats.setText(realFats+"");

    }
}
