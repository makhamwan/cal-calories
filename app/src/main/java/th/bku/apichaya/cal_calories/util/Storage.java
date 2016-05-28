package th.bku.apichaya.cal_calories.util;

import java.util.ArrayList;
import java.util.List;

import th.bku.apichaya.cal_calories.model.Exercise;
import th.bku.apichaya.cal_calories.model.Food;

/**
 * Created by makham on 17/4/2559.
 */
public class Storage {
    private static Storage instance;

    List foods = new ArrayList<Food>();
    List exercises = new ArrayList<Exercise>();

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

    public void addNewFood(String name, double cal){
        foods.add(new Food.Builder(name).calories(cal).build());
    }


    // Exercise
    public void exerciseProvider(){


    }

    public List<Exercise> getExerciseList(){ return exercises; }



//
//	public static void main(String[] args){
//
//		Storage storage = Storage.getInstances();
//		for (int i = 0 ; i < storage.getFoodList().size() ; i++){
//			System.out.println(storage.getFoodList().get(i));
//		}
//
//	}


}