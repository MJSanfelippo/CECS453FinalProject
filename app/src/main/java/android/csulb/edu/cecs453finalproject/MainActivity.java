package android.csulb.edu.cecs453finalproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int id;
    private LinearLayout foodList;
    TextView viewClicked;

    FoodItem foodItem;

    TextView goalCalories;
    TextView currentCalories;
    TextView remainingCalories;

    private int goalCaloriesNumber;
    private int currentCaloriesNumber;
    private int remainingCaloriesNumber;


    Button addFoodButton;

    public void addNew(FoodItem foodItem){
        TextView test = new TextView(getApplicationContext());
        test.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        test.setText(foodItem.getName());
        test.setTag(foodItem);
        test.setId(id++);
        currentCaloriesNumber+=foodItem.getCalories();
        remainingCaloriesNumber-=foodItem.getCalories();
        currentCalories.setText("Current: " + currentCaloriesNumber);
        remainingCalories.setText("Remaining: " + remainingCaloriesNumber);
        registerForContextMenu(test);
        int dp = Formulas.convertToDp(20, getResources().getDisplayMetrics().density);
        test.setPadding(dp,dp,dp,dp);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodItem foodItem = (FoodItem) view.getTag();
                Toast.makeText(getApplicationContext(), foodItem.getName()+" " + foodItem.getCalories(), Toast.LENGTH_LONG).show();

            }
        });
        test.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });
        foodList.addView(test);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = 0;
        getSupportActionBar().setTitle("Health Buddy");
        foodList = (LinearLayout) findViewById(R.id.foodList);

        goalCalories = (TextView) findViewById(R.id.goalCalories);
        currentCalories = (TextView) findViewById(R.id.currentCalories);
        remainingCalories = (TextView) findViewById(R.id.remainingCalories);

        addFoodButton = (Button) findViewById(R.id.addFoodButton);

        goalCaloriesNumber = 1500;
        currentCaloriesNumber = 0;
        remainingCaloriesNumber = 1500;

        goalCalories.setText("Goal: " + goalCaloriesNumber);
        currentCalories.setText("Current: " + currentCaloriesNumber);
        remainingCalories.setText("Remaining: " + remainingCaloriesNumber);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewFoodActivity.class);
                startActivityForResult(intent, 69);
            }
        });
        addNew(new FoodItem("food1", 33, id));
        addNew(new FoodItem("food2", 5, id));
        addNew(new FoodItem("food3", 3, id));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        viewClicked = (TextView) v;
        menu.add(0, 1, 0, "edit");
        menu.add(0, 2, 1, "delete");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item){

        if (item.getTitle().toString().equals("delete")){
            foodList.removeView(viewClicked);

            FoodItem foodItem = (FoodItem) viewClicked.getTag();

            currentCaloriesNumber-=foodItem.getCalories();
            remainingCaloriesNumber+=foodItem.getCalories();
            currentCalories.setText("Current: " + currentCaloriesNumber);
            remainingCalories.setText("Remaining: " + remainingCaloriesNumber);

        } else {
            Toast.makeText(getApplicationContext(), "this is: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.profile){
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 69) {
            if(resultCode == Activity.RESULT_OK){
                Bundle data = intent.getExtras();
                String name = data.getString("name");
                int calories = data.getInt("calories");
                int carbs = 0;
                int fats = 0;
                int proteins = 0;
                if (data.containsKey("carbs")){
                    carbs = data.getInt("carbs");
                }
                if (data.containsKey("fats")){
                    fats = data.getInt("fats");
                }
                if (data.containsKey("proteins")){
                    proteins = data.getInt("proteins");
                }
                addNew(new FoodItem(name, calories, carbs, fats, proteins, id));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

}
