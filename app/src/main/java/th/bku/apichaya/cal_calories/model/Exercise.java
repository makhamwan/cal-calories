package th.bku.apichaya.cal_calories.model;

/**
 * Created by makham on 28/5/2559.
 */
public class Exercise implements CountVariable{

    private double calories;
    private String name;

    public Exercise (Builder builder){
        this.calories = builder.calories;
        this.name = builder.name;

    }

    public static class Builder{
        private double calories;
        private String name;
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

        public Exercise build(){
            return new Exercise(this);
        }

    }

    @Override
    public double getCalories() {
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
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

}

