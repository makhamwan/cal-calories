package th.bku.apichaya.cal_calories.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Observable;
import java.util.Observer;

import th.bku.apichaya.cal_calories.activities.BMRActivity;
import th.bku.apichaya.cal_calories.util.Storage;

/**
 * Created by makham on 28/5/2559.
 */
public class CaloriesCalculator extends Observable implements Observer{

    private static CaloriesCalculator instance;
    private double total;
    private double currentUse;
    private double currentGain;
    private double currentRemain;

    private CaloriesCalculator(){
        total = 1200;
        currentUse = 0;
        currentGain = 0;
        currentRemain = total;
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
        this.currentGain+= (exercise.getTime()*exercise.getCalories());
        this.currentRemain+= (exercise.getTime()*exercise.getCalories());
        notifyObservers();
        setChanged();
    }

    public void deleteExercise(Exercise exercise){
        this.currentGain-=(exercise.getTime()*exercise.getCalories());
        this.currentRemain-=(exercise.getTime()*exercise.getCalories());
        setChanged();
        notifyObservers();
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable observable, Object data) {
        int newTotal = (int) data;

        double remain = this.getCurrentRemain();
        this.setCurrentRemain((remain+newTotal)-total);
        this.setTotal(newTotal);

        System.out.println(" new total " + newTotal);
        System.out.println(" new remain " + this.getCurrentRemain());

        notifyObservers();
        setChanged();
    }

    public void setCurrentRemain(double currentRemain) {
        this.currentRemain = currentRemain;
        setChanged();
        notifyObservers();
    }
}
