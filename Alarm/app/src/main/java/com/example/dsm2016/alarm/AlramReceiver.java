package com.example.dsm2016.alarm;

import android.app.*;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

/**
 * Created by dsm2016 on 2017-08-02.
 */

/*public class AlramReceiver extends BroadcastReceiver {
  /*  private int YOURAPP_NOTIFICATION_ID;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,R.string.app_name,Toast.LENGTH_LONG).show();
    }
    private void showNotification(Context context,int statusBarIconID,String statusBarTextID,String detailedID){
        Intent contnetInten=new Intent(context,MainActivity.class);
        PendingIntent p=PendingIntent.getActivity(context,0,contnetInten,PendingIntent.FLAG_UPDATE_CURRENT);
        CharSequence from="알람";
        CharSequence message="무슨짓을 해야 알람이 꺼질까요?";
        Notification notif=new Notification(statusBarIconID,null,System.currentTimeMillis());
        notif.sound= Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,"6");
        notif.flags=Notification.FLAG_INSISTENT;
        //notif.set(context, from, message, p);
        notif.ledARGB= Color.GREEN;
        NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1234,notif);

    }
}*/
