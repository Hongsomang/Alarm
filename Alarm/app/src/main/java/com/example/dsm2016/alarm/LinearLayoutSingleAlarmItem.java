package com.example.dsm2016.alarm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by dsm2016 on 2017-08-02.
 */

public class LinearLayoutSingleAlarmItem extends LinearLayout {
    Context mContext;
    TextView textView;
    Button AlarmItemCancel;
    AlarmData alarmData;

    private int position;

    public  LinearLayoutSingleAlarmItem(Context context){
        super(context);
        mContext=context;

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=inflater.inflate(R.layout.single_alarm_data_layout,this);

        textView=(TextView)layout.findViewById(R.id.textViewTime);

        AlarmItemCancel=(Button)findViewById(R.id.AlarmItemCancel);
        AlarmItemCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onRemoveButtonOnClickListner!=null)
                    onRemoveButtonOnClickListner.onClicked(alarmData.hh,alarmData.mm,alarmData.reqCode,position);
            }
        });
    }
    public interface OnRemoveButtonOnClickListner{
        void onClicked(int hh,int mm,int reqCode, int position);


    }
    OnRemoveButtonOnClickListner onRemoveButtonOnClickListner;

    void  setOnRemoveButtonOnClickListner(OnRemoveButtonOnClickListner onRemoveButtonOnClickListner){
        this.onRemoveButtonOnClickListner=onRemoveButtonOnClickListner;

    }
    public boolean setData(AlarmData alarmData,int position){
        this.alarmData=alarmData;
        this.position=position;

        textView.setText(alarmData.hh+":"+alarmData.mm+"and requestCode:"+alarmData.reqCode);

    return true;

    }
}
