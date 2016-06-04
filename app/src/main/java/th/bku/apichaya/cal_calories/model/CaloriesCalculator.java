package th.bku.apichaya.cal_calories.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Observable;
import java.util.Observer;

import th.bku.apichaya.cal_calories.util.Storage;

/**
 * Created by makham on 28/5/2559.
 */
public class CaloriesCalculator extends Observable {

    private static CaloriesCalculator instance;
    private double total;
    private double currentUse;
    private double currentGain;
    private double currentRemain;

    private CaloriesCalculator(){
        currentUse = 0;
        currentGain = 0;
        currentRemain = 1200;
    }

    public static CaloriesCalculator getInstances(){
        if (instance == null) instance = new CaloriesCalculator();
        return instance;
    }

    public double getCurrentUse(){ return this.currentUse; }
    public double getCurrentGain(){	return this.currentGain; }
    public double getCurrentRemain(){ return this.currentRemain; }

    public void addFood(Food food){
        this.currentUse+=food.getCalories();
        this.currentRemain-=food.getCalories();
        setChanged();
        notifyObservers();
    }

    public void deleteFood(Food food){
        this.currentUse-=food.getCalories();
        this.currentRemain+=food.getCalories();
        setChanged();
        notifyObservers();
    }

    public void addExercise(Exercise exercise){
        this.currentGain+=exercise.getCalories();
        this.currentRemain+=exercise.getCalories();
        notifyObservers();
        setChanged();
    }

    public void deleteExercise(Exercise exercise){
        this.currentGain-=exercise.getCalories();
        this.currentRemain-=exercise.getCalories();
        setChanged();
        notifyObservers();
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
