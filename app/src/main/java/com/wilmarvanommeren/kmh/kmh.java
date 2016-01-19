package com.wilmarvanommeren.kmh;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.NumberPicker;

public class kmh extends ActionBarActivity implements View.OnClickListener {
    private Button calc;
    private TextView speed;
    private EditText dist;
    private NumberPicker hour,min,sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kmh);
        init();
    }

    private void init(){
        calc = (Button)findViewById(R.id.calc);

        dist = (EditText)findViewById(R.id.dist);
        dist.setText("0");

        hour = (NumberPicker)findViewById(R.id.hour);
        hour.setMaxValue(24);
        hour.setMinValue(0);
        hour.setWrapSelectorWheel(false);

        min = (NumberPicker)findViewById(R.id.min);
        min.setMaxValue(59);
        min.setMinValue(0);
        min.setWrapSelectorWheel(false);

        sec = (NumberPicker)findViewById(R.id.sec);
        sec.setMaxValue(59);
        sec.setMinValue(0);
        sec.setWrapSelectorWheel(false);

        speed = (TextView)findViewById(R.id.speed);

        calc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String distance = dist.getText().toString();
        String hours = ""+hour.getValue();
        String minutes = ""+min.getValue();
        String seconds = ""+sec.getValue();
        switch(view.getId()){
            case R.id.calc:
                try{
                    int hoursec = Integer.parseInt(hours) * 3600;
                    int minsec = Integer.parseInt(minutes) * 60;
                    int totalsec = hoursec + minsec + Integer.parseInt(seconds);
                    float seckm = totalsec / Float.parseFloat(distance);
                    float hkm = seckm/3600;
                    float result = 1/hkm;

                    if (totalsec == 0){
                        speed.setText("No time defined!");
                    } else {if (Float.parseFloat(distance) == 0){
                        speed.setText("No distance defined!");
                    } else {
                        speed.setText(String.format("%.2f", result).concat(" km/h"));
                    }
                    }

                    break;
                } catch (Exception e){
                    speed.setText("All fields are required!");
                }
        }

    }
}