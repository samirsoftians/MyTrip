package com.example.currentpositionapp;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class CurrentLocationService extends Service {

    private static Timer timer = new Timer();
    MyService.OnlineThread thread = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {

        timer.scheduleAtFixedRate(new CurrentLocationService.mainTask2(), 1, 5000);


    }

    private class mainTask2 extends TimerTask {
        public void run() {
            taskHandler.sendEmptyMessage(0);
        }
    }

    @SuppressLint("HandlerLeak")
    private final Handler taskHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            boolean bool = isOnline();
            if (bool == true) {
                // mDataLodingStatusMessageView.setText(R.string.data_fetching_progress_in);

                Log.e("2 MIN TIMER", " CALLED");

             //  thread = new MyService.OnlineThread();//starting network library
                thread.execute((Void) null);



            } else {
                Toast.makeText(getApplicationContext(), "No Internet connection.",
                        Toast.LENGTH_LONG).show();
                Log.e("MyService", "inside else");
            }

        }
    };

    public boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        boolean result = false;
        if (ni != null) {
            if (ni.getState() == NetworkInfo.State.CONNECTED) {
                result = true;
            }
        }
        return result;
    }

}
