package com.example.dsm2016.alarm;

import android.util.Log;

/**
 * Created by dsm2016 on 2017-08-02.
 */

public class AlarmData {
    public int hh;
    public int mm;
    public int reqCode;
    public AlarmData(int hh,int mm,int reqCode){
        this.hh=hh;
        this.mm=mm;
        this.reqCode=reqCode;
    }

    @Override
    public String toString() {
        Log.d(" AlarmData","입니다");
        return hh+":"+mm+":"+"and requestCode:"+reqCode;
    }
}
