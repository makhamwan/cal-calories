package th.bku.apichaya.cal_calories.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import th.bku.apichaya.cal_calories.R;
import th.bku.apichaya.cal_calories.model.CaloriesCalculator;
import th.bku.apichaya.cal_calories.model.Exercise;
import th.bku.apichaya.cal_calories.util.Storage;

public class NewExerciseActivity extends AppCompatActivity {
    private AutoCompleteTextView inputName;
    private AutoCompleteTextView inputCal;
    private AutoCompleteTextView inputTime;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFloatingButton();
        initComponents();
    }

    public void initComponents(){
        inputName = (AutoCompleteTextView)findViewById(R.id.input_exercise_name);
        inputCal = (AutoCompleteTextView)findViewById(R.id.input_exercise_cal);
        inputTime = (AutoCompleteTextView)findViewById(R.id.input_exercise_time);
        button = (Button) findViewById(R.id.button_add_new_exercise);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                double calories;
                double time;
                if(String.valueOf(inputCal.getText())!=null){
                    try{
                        calories = Double.parseDouble(String.valueOf(inputCal.getText()));
                    }catch(NumberFormatException e){
                        System.out.println("cal is null");
                        return ;
                    }

                    time = 1;

                    try{
                        time = Double.parseDouble(String.valueOf(inputTime.getText()));
                    }catch(NumberFormatException e){ return ;}

                    name = "Quick Add";

                    if(inputName!=null) name = String.valueOf(inputName.getText());

                    Exercise exercise = new Exercise.Builder(name).calories(calories).time(time).build();

                    Storage.getInstances().addNewExercise(exercise);
                    Storage.getInstances().addDoneExercise(exercise);
                    CaloriesCalculator.getInstances().addExercise(exercise);

                }

                Intent intent = new Intent(NewExerciseActivity.this, MainActivity.class);
                startActivityForResult(intent,RESULT_OK);
            }
        });

    }

    public void initFloatingButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Do you want to add an exercise from the list?", Snackbar.LENGTH_LONG)
                        .setAction("Click here!", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(NewExerciseActivity.this, ExerciseActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }

        });
    }

    public static class BMRActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bmr);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

    }
}
