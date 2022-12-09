package com.example.avdeev_pr25;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MyService extends Service { //ServiceSimple

    final String LOG_TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        Toast.makeText(getApplicationContext(), "Сервис1 запущен", Toast.LENGTH_LONG).show();
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Сервис1 уничтожен", Toast.LENGTH_LONG).show();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    void someTask() {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i<=5; i++) {
                    String msg = "i" + i;
                    Log.d(LOG_TAG, "i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }
}