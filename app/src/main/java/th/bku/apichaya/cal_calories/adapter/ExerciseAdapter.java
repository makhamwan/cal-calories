package th.bku.apichaya.cal_calories.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import th.bku.apichaya.cal_calories.R;
import th.bku.apichaya.cal_calories.model.Exercise;
import th.bku.apichaya.cal_calories.util.Storage;

/**
 * Created by makham on 28/5/2559.
 */
public class ExerciseAdapter extends ArrayAdapter<Exercise> {
    private List<Exercise> exercises;

    public ExerciseAdapter(Context context, int resource, List<Exercise> objects) {
        super(context, resource, objects);
        exercises = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.exercise_cell, null);
        }

        TextView exercise_name = (TextView) v.findViewById(R.id.exercise_name);
        TextView tv_cal = (TextView) v.findViewById(R.id.tv_cal);
        TextView exercise_cal = (TextView) v.findViewById(R.id.exercise_cal);
        exercise_name.setText(exercises.get(position).getName());
        exercise_cal.setText(exercises.get(position).getCalories() + " cal");

        return v;
    }
}
