package com.example.dsm2016.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;

/**
 * Created by dsm2016 on 2017-08-02.
 */

public class MainActivity extends AppCompatActivity  {
    AdapterAlarm adapterAlarm;
    ListView alarmlistview;
    Button stopwatch,timer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ImageButton plus=(ImageButton)findViewById(R.id.plusbtn);
        plus.setOnClickListener(plusonClick);
        alarmlistview=(ListView) findViewById(R.id.alarmlistview);
        alarmlistview.setAdapter(adapterAlarm);
        stopwatch=(Button)findViewById(R.id.StopWatchBtn);
        stopwatch.setOnClickListener(stopwatchonClick);
        timer=(Button)findViewById(R.id.timerbtn);
        timer.setOnClickListener(timeronClick);
        Log.d("메인","입니다");
    }
    View.OnClickListener timeronClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(),TimerActivity.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener stopwatchonClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(),StopwatchActivity.class);
            startActivity(intent);
            finish();
        }
    };
    View.OnClickListener plusonClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(getApplicationContext(),AlarmActivity.class);
            startActivity(intent);
        }
    };
}
