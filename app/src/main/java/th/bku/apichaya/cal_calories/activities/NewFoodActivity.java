package th.bku.apichaya.cal_calories.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;

import th.bku.apichaya.cal_calories.R;

public class NewFoodActivity extends AppCompatActivity {
    private AutoCompleteTextView inputName;
    private AutoCompleteTextView inputCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFloatingButton();

    }

    public void initComponents(){
        inputName = (AutoCompleteTextView)findViewById(R.id.input_food_name);
//        inputCal = (AutoCompleteTextView)findViewById(R.id.input_food_cal);

    }

    public void initFloatingButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Do you want to add a food from the list?", Snackbar.LENGTH_LONG)
                        .setAction("Click here!", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(NewFoodActivity.this, FoodActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }

        });
    }

}
