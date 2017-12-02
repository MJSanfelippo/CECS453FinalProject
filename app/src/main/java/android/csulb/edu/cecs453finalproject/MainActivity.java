package android.csulb.edu.cecs453finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout foodList;
    private TextView viewClicked;

    private FoodItem foodItem;

    private TextView goalCalories;
    private TextView currentCalories;
    private TextView remainingCalories;

    private int goalCaloriesNumber;
    private int currentCaloriesNumber;
    private int remainingCaloriesNumber;

    private String date;

    private Button addFoodButton;

    public void addNew(FoodItem foodItem){
        TextView test = new TextView(getApplicationContext());
        test.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        test.setText(foodItem.getName());
        test.setTag(foodItem);
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

                Bundle b = new Bundle();
                b.putString("name", foodItem.getName());
                b.putDouble("calories", foodItem.getCalories());
                b.putDouble("fats", foodItem.getFats());
                b.putDouble("proteins", foodItem.getProtein());
                b.putDouble("carbs", foodItem.getCarbs());

                Intent intent = new Intent(MainActivity.this, DisplayFoodInfoActivity.class);
                intent.putExtras(b);
                startActivity(intent);

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

    public void addAll(String date){
        FoodDB db = new FoodDB(getApplicationContext());
        Log.d("test", "do i get here");
        ArrayList<FoodItem> itemList = db.getAllFoodsFromDate(date);
        for (FoodItem item: itemList){
            addNew(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Health Buddy");

        date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        foodList = (LinearLayout) findViewById(R.id.foodList);

        goalCalories = (TextView) findViewById(R.id.goalCalories);
        currentCalories = (TextView) findViewById(R.id.currentCalories);
        remainingCalories = (TextView) findViewById(R.id.remainingCalories);

        addFoodButton = (Button) findViewById(R.id.addFoodButton);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("userSettings", 0);
        double currentWeight = settings.getFloat("currentWeight", 150);
        double goalWeight = settings.getFloat("goalWeight", Float.parseFloat(Double.toString(currentWeight)));
        double currentWeightInKg = Formulas.convertLbToKg(currentWeight);
        int feet = settings.getInt("feet", 5);
        int inches = settings.getInt("inches", 6);
        int height = Formulas.convertToCm(feet, inches);
        int age = settings.getInt("age", 25);
        boolean isMale;
        if (settings.getString("gender", "male").equals("male")){
            isMale = true;
        } else {
            isMale = false;
        }
        goalCaloriesNumber = Formulas.harrisBenedictEquation(currentWeightInKg, height, age, isMale); // double, int, int, bool
        if (currentWeight > goalWeight){
            goalCaloriesNumber -= 400;
        } else if (currentWeight < goalWeight){
            goalCaloriesNumber += 400;
        }
        currentCaloriesNumber = 0;
        remainingCaloriesNumber = goalCaloriesNumber-currentCaloriesNumber;

        goalCalories.setText("Goal: " + goalCaloriesNumber);
        currentCalories.setText("Current: " + currentCaloriesNumber);
        remainingCalories.setText("Remaining: " + remainingCaloriesNumber);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewFoodActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
        addAll(date);


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
            FoodDB db = new FoodDB(getApplicationContext());
            db.deleteFood(foodItem);

        } else {
            /*
             * Nam Nguyen
             */
            FoodItem foodItem = (FoodItem) viewClicked.getTag();

            Bundle b = new Bundle();
            b.putSerializable("FoodItem", foodItem);

            Intent intent = new Intent(MainActivity.this, EditFoodInfoActivity.class);
            intent.putExtras(b);
            startActivity(intent);
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
