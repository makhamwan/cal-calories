package th.bku.apichaya.cal_calories.model;

import java.util.Observable;

/**
 * Created by Kanoon on 6/5/2016.
 */
public class BMRCalculator extends Observable {

    private static BMRCalculator instance;
    private double height;
    private double weight;
    private double age;
    private char gender;
    private int totalCal;




    private BMRCalculator(){
        totalCal = 1200;
    }

    public static BMRCalculator getInstance(){
        if (instance == null) return new BMRCalculator();
        else return instance;
    }

    public void calculate(){
        if (gender=='m'||gender=='M') totalCal = (int) Math.round(66 + ( 13.7 * weight ) + ( 5 * height ) - ( 6.8 * age )) ;
        else totalCal =  (int) Math.round(655 + ( 9.6 * weight ) + ( 1.8 * height ) - ( 4.7 * age ));
        setChanged();
        notifyObservers();
    }

    public void setHeight(double height) {
        this.height = height;
        System.out.println("Set H");
        setChanged();
        notifyObservers();
    }

    public void setWeight(double weight) {
        this.weight = weight;
        System.out.println("Set W");
        setChanged();
        notifyObservers();

    }

    public void setAge(double age) {
        this.age = age;
        System.out.println("Set A");
        setChanged();
        notifyObservers();
    }

    public void setGender(char gender) {
        this.gender = gender;
        System.out.println("Set A");
        setChanged();
        notifyObservers();
    }

    public int getTotalCal(){
        return this.totalCal;
    }

}
