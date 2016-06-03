package th.bku.apichaya.cal_calories.model;

/**
 * Created by makham on 28/5/2559.
 */
public class Food implements  CountVariable{

    private double calories;
    private String name ;

    public Food(Builder builder) {
        this.calories = builder.calories;
        this.name = builder.name;
    }

    public static class Builder{
        private double calories;
        private String name ;
        public Builder(String name){
            this.name = name;
        }
        public Builder calories(double calories){
            this.calories = calories;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Food build(){
            return new Food(this);
        }

    }


    @Override
    public double getCalories() {
        // TODO Auto-generated method stub
        return this.calories;
    }

    @Override
    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String toString (){
        return String.format("%s %f ", name, calories);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
