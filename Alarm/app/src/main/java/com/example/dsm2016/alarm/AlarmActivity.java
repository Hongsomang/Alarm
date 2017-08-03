package com.example.dsm2016.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dsm2016 on 2017-08-02.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class AlarmActivity extends AppCompatActivity {
    private AlarmManager mManager;
    private Context mContext;
    private GregorianCalendar mCalendar;
    private TimePicker mTime;
    ArrayList<AlarmData> arrayListAlarmTimeItem = new ArrayList<AlarmData>();
    public static final int DEFAULT_ALARM_REQUEST = 8000;
    private NotificationManager mNotification;
    Button btnAddAlarm;
    GregorianCalendar currentCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedEditor;
    AdapterAlarm arrayAdaterAlarmList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);
        mContext=getApplicationContext();
        mTime=(TimePicker)findViewById(R.id.time_picker);
        btnAddAlarm=(Button)findViewById(R.id.savebtn);
        sharedPref=getPreferences(Context.MODE_PRIVATE);
        sharedEditor=sharedPref.edit();
        mTime.setIs24HourView(false);
        arrayAdaterAlarmList=new AdapterAlarm(mContext,arrayListAlarmTimeItem);
        mManager=(AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        //mManager.setTimeZone("GMT+09:00");

        Log.d("알람 ","페이지");
        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hh=mTime.getCurrentHour();
                int mm=mTime.getCurrentMinute();
                int reqCode=DEFAULT_ALARM_REQUEST+arrayListAlarmTimeItem.size();
                int i=arrayListAlarmTimeItem.size();
                arrayListAlarmTimeItem.add(new AlarmData(hh,mm,reqCode));
                sharedEditor.putInt("list"+i+"hh",hh);
                sharedEditor.putInt("list"+i+"mm", mm);
                sharedEditor.putInt("list"+i+"reqCode", reqCode);
                sharedEditor.putInt("size", i);
                sharedEditor.commit();
                arrayAdaterAlarmList.notifyDataSetChanged();
               GregorianCalendar gregorianCalendar=new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"));
                int currentYY=currentCalendar.get(Calendar.YEAR);
                int currentMM=currentCalendar.get(Calendar.MONTH);
                int currentDD=currentCalendar.get(Calendar.DAY_OF_MONTH);
                gregorianCalendar.set(currentYY,currentMM,currentDD+1,hh,mm,00);
                if(gregorianCalendar.getTimeInMillis()<currentCalendar.getTimeInMillis()){
                    gregorianCalendar.set(currentYY,currentMM,currentDD,hh,mm,00);
                    Log.i("TAG",gregorianCalendar.getTimeInMillis()+":");
                }
                Intent intent=new Intent(AlarmActivity.this,AlarmdeTimeShowActivity.class);
                intent.putExtra("time", hh+":"+mm);
                intent.putExtra("data", "알람: " + currentCalendar.getTime().toLocaleString());
                intent.putExtra("reqCode", reqCode);
                Toast.makeText(mContext, "reqCode : "+reqCode, Toast.LENGTH_LONG).show();
                PendingIntent pi=PendingIntent.getActivity(AlarmActivity.this,reqCode,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                mManager.setRepeating(AlarmManager.RTC_WAKEUP,gregorianCalendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pi);
                Intent list=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(list);
                Log.d("저장","버튼");


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","start");
        arrayListAlarmTimeItem.clear();
        int size=sharedPref.getInt("size",0);
        if(size!=0){
            for(int i=0;i<size+1;i++){
                int hh=sharedPref.getInt("list"+i+"reCode",0);
                int mm = sharedPref.getInt("list"+i+"mm", 0);
                int reqCode=sharedPref.getInt("list"+i+"reCode",0);
                arrayListAlarmTimeItem.add(new AlarmData(hh,mm,reqCode));
            }
        }arrayAdaterAlarmList.notifyDataSetChanged();
    }
}
