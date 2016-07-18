package com.example.hzoubir.haitamutilsapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by hzoubir on 7/5/16.
 */
public class InternetReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent){

        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
        Notification noti = new Notification.Builder(context)
                .setContentTitle("Internet Connection Changed")
                .setContentText("Internet Connection Changed")
                .setSmallIcon(R.drawable.common_ic_googleplayservices)
                .build();
        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
       // mNotifyMgr.notify(mNotificationId,noti);

        Intent intent1 = new Intent(context, MyService.class);
        context.startService(intent1);

    }
}
