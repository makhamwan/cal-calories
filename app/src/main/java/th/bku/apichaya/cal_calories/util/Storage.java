package th.bku.apichaya.cal_calories.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import th.bku.apichaya.cal_calories.model.CaloriesCalculator;
import th.bku.apichaya.cal_calories.model.Exercise;
import th.bku.apichaya.cal_calories.model.Food;

/**
 * Created by makham on 17/4/2559.
 */
public class Storage {
    private static Storage instance;
    private List foods = new ArrayList<Food>();
    private List exercises = new ArrayList<Exercise>();
    private List eatenFoods = new ArrayList<Food>();
    private List doneExercises = new ArrayList<Exercise>();

    private Storage(){
        foodProvider();
        exerciseProvider();

    }

    public static Storage getInstances(){
        if (instance == null) instance = new Storage();
        return instance;
    }

    // Food
    public void foodProvider(){
        Food friedRice = new Food.Builder("Fried Rice").calories(580).build();
        Food porkBoiledRice = new Food.Builder("Pork boiled Rice").calories(228).build();
        Food instantNoodle = new Food.Builder("instant Noodle").calories(230).build();
        Food wontonSoup = new Food.Builder("Wonton Soup").calories(350).build();
        Food potatoChips = new Food.Builder("Potato Chips").calories(350).build();


        foods.add(friedRice);
        foods.add(porkBoiledRice);
        foods.add(instantNoodle);
        foods.add(wontonSoup);
        foods.add(potatoChips);
    }

    public List<Food> getFoodList(){ return foods; }
    public List<Food> getEatenFoodList(){ return eatenFoods; }
    public void addEatenFoods(Food food){
        eatenFoods.add(food);
    }
    public void addNewFood(String name, double cal){ foods.add(new Food.Builder(name).calories(cal).build());}
    public void deleteEatenFoodList(int i){ this.eatenFoods.remove(i); }


    // Exercise
    public void exerciseProvider(){
        Exercise hullahoop = new Exercise.Builder("Hullahoop").calories(137).time(1).build();
        Exercise jogging = new Exercise.Builder("Jogging").calories(123).time(1).build();
        Exercise joggingInPalce = new Exercise.Builder("Jogging In Place").time(1).calories(467).build();
        Exercise swimming = new Exercise.Builder("swimming").calories(326).time(1).build();
        Exercise aerobic = new Exercise.Builder("Aerobic").calories(222).time(1).build();

        exercises.add(hullahoop);
        exercises.add(jogging);
        exercises.add(jogging);
        exercises.add(joggingInPalce);
        exercises.add(swimming);
        exercises.add(aerobic);
    }

    public List<Exercise> getExerciseList(){ return exercises; }
    public List<Exercise> getDoneExerciseList(){ return  doneExercises; }
    public void addDoneExercise(Exercise exercise){doneExercises.add(exercise);}
    public void addNewExercise(Exercise exercise){
        exercises.add(new Exercise.Builder(exercise.getName()).calories(exercise.getCalories()).time(exercise.getTime()).build());
    }
    public void deleteDoneExercise(int i){ this.doneExercises.remove(i);}

}
