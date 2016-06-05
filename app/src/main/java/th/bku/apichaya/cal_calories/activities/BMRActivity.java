package th.bku.apichaya.cal_calories.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;

import th.bku.apichaya.cal_calories.R;
import th.bku.apichaya.cal_calories.model.BMRCalculator;
import th.bku.apichaya.cal_calories.model.CaloriesCalculator;

public class BMRActivity extends AppCompatActivity {
    private TextView inputW;
    private TextView inputH;
    private TextView inputA;
    private TextView inputG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFloatingButton();
    }

    public void initFloatingButton(){
        inputW = (TextView) findViewById(R.id.input_weight);
        inputH = (TextView) findViewById(R.id.input_height);
        inputA = (TextView) findViewById(R.id.input_age);
        inputG = (TextView) findViewById(R.id.input_gender);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                double w;
                double h;
                double a;
                char g = 'm';

                try{
                    w = Double.parseDouble(String.valueOf(inputW.getText()));
                    h = Double.parseDouble(String.valueOf(inputH.getText()));
                    a = Double.parseDouble(String.valueOf(inputA.getText()));


                }catch(NumberFormatException e){
                    return ;
                }

                if ( String.valueOf(inputG.getText())!=null){
                    g = String.valueOf(inputG.getText()).charAt(0);
                }

                if (g=='m' || g=='M'){
                    CaloriesCalculator.getInstances().setCurrentRemain(( (CaloriesCalculator.getInstances().getCurrentRemain() - CaloriesCalculator.getInstances().getTotal()) + Math.round(66 + ( 13.7 * w ) + ( 5 * h ) - ( 6.8 * a ))));
                    CaloriesCalculator.getInstances().setTotal( Math.round(66 + ( 13.7 * w ) + ( 5 * h ) - ( 6.8 * a )));
                }
                else if (g=='f' || g=='F'){
                    CaloriesCalculator.getInstances().setCurrentRemain(( (CaloriesCalculator.getInstances().getCurrentRemain() - CaloriesCalculator.getInstances().getTotal()) + Math.round(655 + ( 9.6 * w ) + ( 1.8 * h ) - ( 4.7 * a ))));
                    CaloriesCalculator.getInstances().setTotal( Math.round(655 + ( 9.6 * w ) + ( 1.8 * h ) - ( 4.7 * a )));
                }

                finish();

            }
        });
    }

}
