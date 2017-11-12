package android.csulb.edu.cecs453finalproject;

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

public class MainActivity extends AppCompatActivity {

    private int id;
    private LinearLayout foodList;
    TextView viewClicked;

    TextView goalCalories;
    TextView currentCalories;
    TextView remainingCalories;

    Button addFoodButton;

    public void addNew(int id){
        TextView test = new TextView(getApplicationContext());
        test.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        test.setText("Tester" + id);
        test.setId(id++);
        registerForContextMenu(test);
        int dp = Formulas.convertToDp(20, getResources().getDisplayMetrics().density);
        test.setPadding(dp,dp,dp,dp);
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
        addNew(5);
        addNew(6);
        addNew(7);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("options");
        viewClicked = (TextView) v;

        menu.add(0, 1, 0, "edit");
        menu.add(0, 2, 1, "delete");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item){

        if (item.getTitle().toString().equals("delete")){
            Toast.makeText(getApplicationContext(), "this is: " + viewClicked.getText().toString(), Toast.LENGTH_SHORT).show();
            foodList.removeView(viewClicked);
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
            Toast.makeText(getApplicationContext(), "You clicked " + item.getTitle(), Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
