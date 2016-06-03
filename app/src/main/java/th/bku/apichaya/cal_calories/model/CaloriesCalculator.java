package th.bku.apichaya.cal_calories.model;

import java.util.Observable;
import java.util.Observer;

import th.bku.apichaya.cal_calories.util.Storage;

/**
 * Created by makham on 28/5/2559.
 */
public class CaloriesCalculator extends Observable{
    private double total;
    private double currentUse;
    private double currentGain;
    private double currentRemain;

    public CaloriesCalculator(){
        currentUse = 0;
        currentGain = 0;
        currentRemain = 1200;
    }

    public double getCurrentUse(){ return this.currentUse; }
    public double getCurrentGain(){	return this.currentGain; }
    public double getCurrentRemain(){ return this.currentRemain; }

    public void setCurrentUse(double c){ this.currentUse = c; }
    public void setCurrentGain(double c){ this.currentGain = c; }
    public void setCurrentRemain(double c){	this.currentRemain = c; }

    public void addFood(Food food){
        this.currentUse+=food.getCalories();
        this.currentRemain-=food.getCalories();
        setChanged();
        notifyObservers();
        System.out.println(""+food.getName() + " has already add");
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
        setChanged();
        notifyObservers();
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
