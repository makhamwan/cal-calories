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
import th.bku.apichaya.cal_calories.model.Food;
import th.bku.apichaya.cal_calories.util.Storage;

public class NewExerciseActivity extends AppCompatActivity {
    private AutoCompleteTextView inputName;
    private AutoCompleteTextView inputCal;
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
        button = (Button) findViewById(R.id.button_add_new_exercise);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(inputCal.getText())!=null){
                    String name = "Quick Add";
                    double calories;
                    if(inputName!=null){
                        name = String.valueOf(inputName.getText());
                    }
                    calories = Double.parseDouble(String.valueOf(inputCal.getText()));
                    Storage.getInstances().addNewExercise(name,calories);
                    Storage.getInstances().addDoneExercise(
                            Storage.getInstances().getExerciseList().get(
                                    Storage.getInstances().getExerciseList().size()-1));
                    CaloriesCalculator.getInstances().addExercise( new Exercise.Builder(name).calories(calories).build());
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

}
