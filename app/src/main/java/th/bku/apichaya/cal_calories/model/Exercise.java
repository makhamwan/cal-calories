package th.bku.apichaya.cal_calories.model;

/**
 * Created by makham on 28/5/2559.
 */
public class Exercise implements CountVariable{

    private double calories;
    private String name;
    private double time;

    public Exercise (Builder builder){
        this.calories = builder.calories;
        this.name = builder.name;
        this.time = builder.time;
    }

    public static class Builder{
        private double calories;
        private String name;
        private double time;
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
        public Builder time(double time){
            this.time = time;
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


    public String toString (){
        return String.format("%s %f ", name, calories);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public double getTime() { return time; }
}

