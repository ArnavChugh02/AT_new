package com.example.alarmapp;

import android.app.*;
import android.content.*;
import android.os.Build;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("Alarm")
                .setContentText("Time's up!")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(101, notification);
    }
}

