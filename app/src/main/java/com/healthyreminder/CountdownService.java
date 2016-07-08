package com.healthyreminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by user on 7/5/2016.
 */
public class CountdownService extends Service {

    CountDownTimer mHourTimer;
    public static final String COUNT_FILTER = "TIMER_DONE";
    Intent broadcaster = new Intent(COUNT_FILTER);

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d("TIMER", "OnDestroy Service");
        mHourTimer.cancel();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TIMER", "OnStartCommand");

        mHourTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("TIMER", "Remaining: " +millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Log.d("TIMER", "onFinish");

                Intent notifIntent = new Intent(CountdownService.this, MainActivity.class);
                PendingIntent intent = PendingIntent.getActivity(CountdownService.this, 0,notifIntent, 0);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder notification = new NotificationCompat.Builder(CountdownService.this)
                        .setContentTitle("ALERT")
                        .setContentText("STAND UP")
                        .setContentIntent(intent)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.icon);
                int mNotificationId = 001;

                notificationManager.notify(mNotificationId, notification.build());

                CountdownService.this.stopSelf();

            }
        };
        Log.d("TIMER", "OnCreate Service");
        mHourTimer.start();

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
