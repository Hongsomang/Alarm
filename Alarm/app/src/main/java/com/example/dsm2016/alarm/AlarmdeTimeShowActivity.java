package com.example.dsm2016.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by dsm2016 on 2017-08-02.
 */

public class AlarmdeTimeShowActivity extends AppCompatActivity {
    TextView AlarmedTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmed_time_show_layout);
        AlarmedTime=(TextView)findViewById(R.id.textViewAlarmedTime);
        Intent intent=getIntent();
        String tiem=intent.getStringExtra("time");
        String data=intent.getStringExtra("data");
        int reqCode=intent.getIntExtra("reCode",0);
        AlarmedTime.setText(tiem+"\n"+data+"\n"+reqCode);
        Log.d("AlarmdeTimeShowActivity","입니다.");
    }
}
