package android.csulb.edu.cecs453finalproject;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout foodList;

    TextView goalCalories;
    TextView currentCalories;
    TextView remainingCalories;

    Button addFoodButton;

    public void addNew(){
        TextView test = new TextView(getApplicationContext());
        test.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        test.setText("Tester");
        int dp = Formulas.convertToDp(20, getResources().getDisplayMetrics().density);
        test.setPadding(dp,dp,dp,dp);
        foodList.addView(test);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Health Buddy");
        foodList = (LinearLayout) findViewById(R.id.foodList);

        goalCalories = (TextView) findViewById(R.id.goalCalories);
        currentCalories = (TextView) findViewById(R.id.currentCalories);
        remainingCalories = (TextView) findViewById(R.id.remainingCalories);

        addFoodButton = (Button) findViewById(R.id.addFoodButton);
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
            Toast.makeText(getApplicationContext(), "You clicked " + item.getTitle(), Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
