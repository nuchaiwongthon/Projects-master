package project.bc.nu.projects.calendar;

/**
 * Created by TKK on 2/14/2018.
 */

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import project.bc.nu.projects.R;

public class AlarmReceiver extends BroadcastReceiver {

    private int rqcode;


    @Override
    public void onReceive(Context context, Intent intent) {


//       Intent i = new Intent(context, ManagerCalendar.class);
//       i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);



        String content = intent.getExtras().getString("extra");
        String title = intent.getExtras().getString("extrat");
        rqcode = intent.getExtras().getInt("rqcode");

        simpleNotification(title,content,context);



    }

    public void simpleNotification(String title,String text,Context context) {


        int notifyId = 001;


        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_app)
                        .setContentTitle(title)
                        .setAutoCancel(true)
                        .setContentText(text);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelId1 = "1";
            String channelName1 = "channel1";


            NotificationChannel channel = new NotificationChannel(channelId1, channelName1, NotificationManager.IMPORTANCE_DEFAULT);


            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            channel.enableVibration(true);


            builder.setChannelId(channelId1);


            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }

        } else {

            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        }



        Intent intent =  new Intent(context.getApplicationContext(),ManagerCalendar.class);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context.getApplicationContext());


        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(rqcode, PendingIntent.FLAG_UPDATE_CURRENT);


        builder.setContentIntent(pendingIntent);




        if (mNotificationManager != null) {
            mNotificationManager.notify(notifyId, builder.build());
        }
    }







}