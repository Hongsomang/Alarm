package com.example.dsm2016.alarm;
import android.content.Intent;
import android.os.Message;
import android.app.Activity;
import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dsm2016 on 2017-08-03.
 */

public class TimerActivity extends AppCompatActivity {
    SharedPreferences prefs;
    Editor ePref;
    Button spend,start,stop,reset,Alarm,Stopwatch ;
    Boolean bool=true;
    EditText in;
    TextView timerView;
    String text,str;
    int t,hour,minute,second;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity);
        spend=(Button)findViewById(R.id.spendbtn);
        start=(Button)findViewById(R.id.startbtn);
        stop=(Button)findViewById(R.id.stopbtn);
        reset=(Button)findViewById(R.id.resetbtn);
        in=(EditText)findViewById(R.id.timerEdit);
        timerView=(TextView)findViewById(R.id.timerView);
        Alarm=(Button)findViewById(R.id.alarmbtn);
        Stopwatch=(Button) findViewById(R.id.StopWatchBtn);

        prefs=getSharedPreferences("Save", Activity.MODE_PRIVATE);
        t=prefs.getInt("t",t);
        setting();
        str=String.format("%02d:%02d:%02d",hour,minute,second);
        timerView.setText(str);
        in.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                in.setText("");
            }
        });
        spend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    text=in.getText().toString();
                    t=Integer.parseInt(text);
                    setting();
                    str=String.format("%02d:%02d:%02d",hour,minute,second);
                    timerView.setText(str);
                }catch (Exception e){

                }
            }
        });
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bool=true;
                thread threadTest=new thread();
                threadTest.setDaemon(true);
                threadTest.start();

            }


        });
        stop.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                bool=false;
            }
        });
        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                bool=false;
                t=0;
                setting();
                str=String.format("%02d:%02d:%02d",hour,minute,second);
                timerView.setText(str);
            }
        });
        Alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StopwatchActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
public class thread extends Thread{
    public void run(){
        while (bool){
            handler.sendEmptyMessage(0);
            try{
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
    }
}
Handler handler=new Handler(){
    public void handleMessage(Message msg){
        if(msg.what==0){
           if(t>0){
               Log.d("fureun","XD");
               t--;
               setting();
               str=String.format("%02d:%02d:%02d",hour,minute,second);
               timerView.setText(str);
           }
        }
    }
};
    private void setting() {
        hour=t/3600;
        minute=(t%3600)/60;
        second=(t%3600)%60;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bool=false;
        ePref=prefs.edit();
        ePref.putInt("t",t);
        ePref.commit();
    }


}
