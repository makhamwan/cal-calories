package th.bku.apichaya.cal_calories.activities;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import th.bku.apichaya.cal_calories.model.Exercise;
import th.bku.apichaya.cal_calories.model.Food;
import th.bku.apichaya.cal_calories.util.Storage;

import static th.bku.apichaya.cal_calories.util.Storage.getInstances;

public class MainActivity extends AppCompatActivity implements Observer {
    private Bundle savedInstanceState;
    private BottomBar bottomBar;

    // calories status
    private TextView total_calories;
    private TextView used_calories;
    private TextView gained_calories;
    private TextView remaining_calories;

    private ListView foodListView;
    private FoodAdapter foodAdapter;

    private ListView exerciseListView;
    private ExerciseAdapter exerciseAdapter;

    //etc
    private boolean start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CaloriesCalculator.getInstances().addObserver(this);
        start = true;
        initBottombar();
        initComponents();
        start = false;
    }

    public void initComponents(){
        // calories status
        total_calories = (TextView) findViewById(R.id.num_total);
        used_calories = (TextView) findViewById(R.id.num_use);
        gained_calories = (TextView) findViewById(R.id.num_gain);
        remaining_calories = (TextView) findViewById(R.id.num_remain);

        total_calories = (TextView) findViewById(R.id.num_total);
        this.used_calories.setText(String.format("%.1f",CaloriesCalculator.getInstances().getCurrentUse()));
        this.gained_calories.setText(String.format("%.1f",CaloriesCalculator.getInstances().getCurrentGain()));
        this.remaining_calories.setText(String.format("%.1f",CaloriesCalculator.getInstances().getCurrentRemain()));

        if(CaloriesCalculator.getInstances().getCurrentRemain()<0){
            this.remaining_calories.setTextColor(Color.RED);
        }

        foodListView = (ListView) findViewById(R.id.food_list_view);
        foodAdapter = new FoodAdapter(this, R.layout.food_cell, getInstances().getEatenFoodList());
        foodListView.setAdapter(foodAdapter);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int i;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Food")
                        .setMessage("Do you want to delete this food from list?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                CaloriesCalculator.getInstances().deleteFood((Food) Storage.getInstances().getEatenFoodList().get(((int)foodListView.getItemIdAtPosition(i))));
                                Storage.getInstances().deleteEatenFoodList(i);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });

        exerciseListView = (ListView) findViewById(R.id.exercise_list_view);
        exerciseAdapter = new ExerciseAdapter(this, R.layout.exercise_cell, getInstances().getDoneExerciseList());
        exerciseListView.setAdapter(exerciseAdapter);

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int i;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Exercise")
                        .setMessage("Do you want to delete this exercise from list?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                CaloriesCalculator.getInstances().deleteExercise((Exercise) Storage.getInstances().getDoneExerciseList().get(((int)exerciseListView.getItemIdAtPosition(i))));
                                Storage.getInstances().deleteDoneExercise(i);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which){}
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });
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
                else if (menuItemId == R.id.foodFromList){
                    if (!start){
                        Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.foodFromList){
                    Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                    startActivity(intent);
                }
                else if(menuItemId == R.id.exerciseFromList){
                    Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
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
        this.used_calories.setText(""+CaloriesCalculator.getInstances().getCurrentUse());
        this.gained_calories.setText(""+CaloriesCalculator.getInstances().getCurrentGain());
        this.remaining_calories.setText(""+CaloriesCalculator.getInstances().getCurrentRemain());
        foodAdapter.notifyDataSetChanged();
        exerciseAdapter.notifyDataSetChanged();
    }
}