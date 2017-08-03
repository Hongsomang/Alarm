package com.example.dsm2016.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dsm2016 on 2017-08-02.
 */

public class AdapterAlarm extends BaseAdapter {
   Context mcontext;
    ArrayList<String> mData;
    LayoutInflater mIflate;
    ArrayList<AlarmData> alarmDataArrayList;

    public AdapterAlarm(Context context,ArrayList<AlarmData> alarmDataArrayList){
        mcontext=context;
        this.alarmDataArrayList=alarmDataArrayList;
        mIflate=LayoutInflater.from(mcontext);
            }
    @Override
    public int getCount() {

        return alarmDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    public boolean removeData(int position){
        alarmDataArrayList.remove(position);
        notifyDataSetChanged();
        return false;
    }

    @Override
    public long getItemId(int position) {

        return alarmDataArrayList.get(position).reqCode;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LinearLayoutSingleAlarmItem layoutSingleAlarmItem=(LinearLayoutSingleAlarmItem) view;
        if(layoutSingleAlarmItem==null){

            layoutSingleAlarmItem=new LinearLayoutSingleAlarmItem(mcontext);
            layoutSingleAlarmItem.setOnRemoveButtonOnClickListner(onRemoveButtonOnClickListner);

        }
        layoutSingleAlarmItem.setData(alarmDataArrayList.get(position),position);
        Log.d("getView","입니다.");
        return layoutSingleAlarmItem;
    }
    LinearLayoutSingleAlarmItem.OnRemoveButtonOnClickListner onRemoveButtonOnClickListner=new LinearLayoutSingleAlarmItem.OnRemoveButtonOnClickListner() {
        @Override
        public void onClicked(int hh, int mm, int reqCode, int position) {
            //Toast.makeText(mcontext,"position:"+position+"reqCode:"+reqCode, 0).show();
            Toast.makeText(mcontext,"position:"+position+"reqCode"+reqCode, Toast.LENGTH_LONG).show();

            AlarmManager alarmManager=(AlarmManager)mcontext.getSystemService(Context.ALARM_SERVICE);

            Intent intent=new Intent(mcontext,AlarmdeTimeShowActivity.class);

            Toast.makeText(mcontext,"reqCode:"+reqCode,Toast.LENGTH_LONG).show();

            PendingIntent pi=PendingIntent.getActivity(mcontext,reqCode,intent,PendingIntent.FLAG_CANCEL_CURRENT);

            alarmManager.cancel(pi);
            removeData(position);
            Log.d("Remove","입니다");
        }
    };

}


