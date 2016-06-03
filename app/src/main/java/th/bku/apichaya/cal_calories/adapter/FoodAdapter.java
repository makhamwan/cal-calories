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
import th.bku.apichaya.cal_calories.model.Food;
import th.bku.apichaya.cal_calories.util.Storage;

/**
 * Created by makham on 28/5/2559.
 */
public class FoodAdapter extends ArrayAdapter<Food> {
    private List<Food> foods;

    public FoodAdapter(Context context, int resource, List<Food> objects) {
        super(context, resource, objects);
        foods = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.food_cell, null);
        }

        TextView food_name = (TextView) v.findViewById(R.id.food_name);
        TextView tv_cal = (TextView) v.findViewById(R.id.tv_cal);
        TextView food_cal = (TextView) v.findViewById(R.id.food_cal);
        food_name.setText(foods.get(position).getName());
        food_cal.setText(foods.get(position).getCalories() + " cal");

        return v;
    }
}
