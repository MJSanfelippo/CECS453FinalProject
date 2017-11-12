package android.csulb.edu.cecs453finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewFoodActivity.this, MainActivity.class);
                startActivity(intent);
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
