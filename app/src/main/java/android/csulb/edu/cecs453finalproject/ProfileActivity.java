package android.csulb.edu.cecs453finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.content.SharedPreferences.Editor;

/**
 * Created by Michael on 11/11/2017.
 */

public class ProfileActivity extends AppCompatActivity{

    Button saveChangesButton;

    private EditText nameEditText;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText currentWeightEditText;
    private EditText goalWeightEditText;

    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;

    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        saveChangesButton = (Button) findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getApplicationContext().getSharedPreferences("userSettings", 0);
                Editor editor = settings.edit();

                String enteredName = nameEditText.getText().toString().trim();
                String enteredAge = ageEditText.getText().toString().trim();
                String enteredFeet = feetEditText.getText().toString().trim();
                String enteredInches = inchesEditText.getText().toString().trim();
                String enteredCurrentWeight = currentWeightEditText.getText().toString().trim();
                String enteredGoalWeight = goalWeightEditText.getText().toString().trim();
                int enteredGenderId = genderRadioGroup.getCheckedRadioButtonId();
                if (enteredGenderId==maleRadioButton.getId()){
                    gender = "male";
                } else {
                    gender = "female";
                }
                editor.putString("gender", gender);

                if (!enteredName.equals("")){
                    editor.putString("name", enteredName);
                }
                if (!enteredAge.equals("")){
                    editor.putInt("age", Integer.parseInt(enteredAge));
                }
                if (!enteredFeet.equals("")){
                    editor.putInt("feet", Integer.parseInt(enteredFeet));
                }
                if (!enteredInches.equals("")){
                    editor.putInt("inches", Integer.parseInt(enteredInches));
                }
                if (!enteredCurrentWeight.equals("")){
                    editor.putFloat("currentWeight", Float.parseFloat(enteredCurrentWeight));
                }
                if (!enteredGoalWeight.equals("")){
                    editor.putFloat("goalWeight", Float.parseFloat(enteredGoalWeight));
                }
                editor.commit();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        nameEditText = (EditText) findViewById(R.id.name);
        ageEditText = (EditText) findViewById(R.id.ageText);
        feetEditText = (EditText) findViewById(R.id.feet);
        inchesEditText = (EditText) findViewById(R.id.inches);
        currentWeightEditText = (EditText) findViewById(R.id.currentWeight);
        goalWeightEditText = (EditText) findViewById(R.id.goalWeight);

        genderRadioGroup = (RadioGroup) findViewById(R.id.genderGroup);

        maleRadioButton = (RadioButton) findViewById(R.id.maleRadioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.femaleRadioButton);



    }



}
