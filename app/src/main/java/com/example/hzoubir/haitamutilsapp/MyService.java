package com.example.hzoubir.haitamutilsapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;

/**
 * Created by hzoubir on 7/5/16.
 */
public class MyService extends IntentService {

    @Override
    public void onHandleIntent(Intent intent){
        sendnotification();


    }

    public MyService(){

        super("MyService");

    }
    private void sendnotification(){

        Notification noti = new Notification.Builder(this)
                .setContentTitle("Internet Connection Changed")
                .setContentText("Service Saying internet connection have changed/ do something")
                .setSmallIcon(R.drawable.common_ic_googleplayservices)
                .build();
        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId,noti);


    }
}
