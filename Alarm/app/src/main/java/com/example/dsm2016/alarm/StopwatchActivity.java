package com.example.dsm2016.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

/**
 * Created by dsm2016 on 2017-08-03.
 */

public class StopwatchActivity extends AppCompatActivity {
    TextView list;
    Chronometer chron;
    Button Alarm,Timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch_activity);
        chron=(Chronometer)findViewById(R.id.stopwatch);
        Alarm=(Button)findViewById(R.id.alarmbtn);
        Alarm.setOnClickListener(AlarmonClick);
        Timer=(Button)findViewById(R.id.timer);
        Timer.setOnClickListener(TimeronClick);
        /*chron.setBase(SystemClock.elapsedRealtime());
       /* chron.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
           public void onChronometerTick(Chronometer chronometer) {
                int sec=(int)(((SystemClock.elapsedRealtime()-chron.getBase())/1000)%5);

            }
        });*/

    }
    View.OnClickListener TimeronClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(),TimerActivity.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener AlarmonClick=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
    public void start(View view){
        chron.start();

    }
    public void stop(View view){
        chron.stop();
       // chron.setBase(SystemClock.elapsedRealtime());
        //chron.setBase(0);
    }
    public void print(View view){
        list=(TextView)findViewById(R.id.watchlist);

        long current=SystemClock.elapsedRealtime()-chron.getBase();
        int time=(int)(current/1000);
        int hour=time/(60*60);
        int min=time%(60*60)/60;
        int sec=time%60;

        list.setText(hour+":"+min+":"+sec+"\n");

    }
    public  void reset(View view){
        list=(TextView)findViewById(R.id.watchlist);

        chron.stop();
        chron.setBase(SystemClock.elapsedRealtime());

        list.setText("");

    }
}
