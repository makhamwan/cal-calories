package th.bku.apichaya.cal_calories.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import java.util.Observable;
import java.util.Observer;
import th.bku.apichaya.cal_calories.R;
import th.bku.apichaya.cal_calories.adapter.ExerciseAdapter;
import th.bku.apichaya.cal_calories.adapter.FoodAdapter;
import th.bku.apichaya.cal_calories.model.CaloriesCalculator;
import static th.bku.apichaya.cal_calories.util.Storage.getInstances;

public class MainActivity extends AppCompatActivity implements Observer {
    private CaloriesCalculator cal;
    private Bundle savedInstanceState;
    private BottomBar bottomBar;

    // calories status
    private TextView total_calories;
    private TextView used_calories;
    private TextView gained_calories;
    private TextView remaining_calories;

    ListView foodListView;
    private FoodAdapter foodAdapter;

    ListView exerciseListView;
    private ExerciseAdapter exerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cal = new CaloriesCalculator();
        //cal.addObserver(this);
        initBottombar();
        initComponents();
    }

    public void initComponents(){
        // calories status
        total_calories = (TextView) findViewById(R.id.num_total);
        used_calories = (TextView) findViewById(R.id.num_use);
        gained_calories = (TextView) findViewById(R.id.num_total);
        remaining_calories = (TextView) findViewById(R.id.num_use);

        foodListView = (ListView) findViewById(R.id.food_list_view);
        foodAdapter = new FoodAdapter(this, R.layout.food_cell, getInstances().getEatenFoodList());
        foodListView.setAdapter(foodAdapter);
    }

    public void initBottombar(){
        // bottom bar
        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.menu_bottom , new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.exerciseFromList){
                    Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.foodFromList){
                    Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void update(Observable observable, Object data) {
        this.total_calories.setText(""+cal.getTotal());
        this.used_calories.setText(""+cal.getCurrentUse());
        this.gained_calories.setText(""+cal.getCurrentUse());
        this.used_calories.setText(""+cal.getCurrentUse());
    }
}
