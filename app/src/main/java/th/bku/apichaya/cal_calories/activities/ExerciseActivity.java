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
import th.bku.apichaya.cal_calories.adapter.ExerciseAdapter;
import th.bku.apichaya.cal_calories.model.CaloriesCalculator;
import th.bku.apichaya.cal_calories.model.Exercise;
import th.bku.apichaya.cal_calories.util.Storage;

import static th.bku.apichaya.cal_calories.util.Storage.getInstances;

public class ExerciseActivity extends AppCompatActivity {
    private AutoCompleteTextView search_field;
    private ExerciseAdapter exerciseAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initcomponents();
        initFloatingButton();
    }

    public void initcomponents(){

        listView = (ListView) findViewById(R.id.exercise_list_view);
        search_field = (AutoCompleteTextView) findViewById(R.id.search_field);
        exerciseAdapter = new ExerciseAdapter(this, R.layout.exercise_cell, getInstances().getExerciseList());
        listView.setAdapter(exerciseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Storage.getInstances().addDoneExercise((Exercise) Storage.getInstances().getExerciseList().get((int)listView.getItemIdAtPosition(i)));
                CaloriesCalculator.getInstances().addExercise((Exercise) Storage.getInstances().getExerciseList().get(((int)listView.getItemIdAtPosition(i))));
                Intent intent = new Intent(ExerciseActivity.this, MainActivity.class);
                startActivityForResult(intent,RESULT_OK);
            }
        });

    }

    public void initFloatingButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "There is no an exercise you look for??", Snackbar.LENGTH_LONG)
                        .setAction("Add New Exercise", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ExerciseActivity.this, NewExerciseActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }

        });
    }

}
