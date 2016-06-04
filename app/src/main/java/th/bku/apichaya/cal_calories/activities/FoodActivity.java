package th.bku.apichaya.cal_calories.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import th.bku.apichaya.cal_calories.R;
import th.bku.apichaya.cal_calories.adapter.FoodAdapter;
import th.bku.apichaya.cal_calories.model.CaloriesCalculator;
import th.bku.apichaya.cal_calories.model.Food;
import th.bku.apichaya.cal_calories.util.Storage;
import static th.bku.apichaya.cal_calories.util.Storage.getInstances;

public class FoodActivity extends AppCompatActivity {
    private AutoCompleteTextView search_field;
    private FoodAdapter foodAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFloatingButton();
        initcomponents();
    }

    public void initcomponents(){
        listView = (ListView) findViewById(R.id.food_list_view);
        search_field = (AutoCompleteTextView) findViewById(R.id.search_field);
        foodAdapter = new FoodAdapter(this, R.layout.food_cell, getInstances().getFoodList());
        listView.setAdapter(foodAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Storage.getInstances().addEatenFoods((Food) Storage.getInstances().getFoodList().get(((int)listView.getItemIdAtPosition(i))));
                CaloriesCalculator.getInstances().addFood((Food) Storage.getInstances().getFoodList().get(((int)listView.getItemIdAtPosition(i))));
                Intent intent = new Intent(FoodActivity.this, MainActivity.class);
                startActivityForResult(intent,RESULT_OK);
            }
        });
    }


    public void initFloatingButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "There is no a food you look for?", Snackbar.LENGTH_LONG)
                        .setAction("Add New Food", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(FoodActivity.this, NewFoodActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }

        });
    }
}
