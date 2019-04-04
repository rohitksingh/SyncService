package rohitksingh.com.syncservice;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import rohitksingh.com.syncservice.Sync.SyncService;

public class TimerService extends SyncService {

    private static final String TAG = "TimerService";

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serviceBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int flagId)
    {
        super.onStartCommand(intent,flag,flagId);
        Log.d(TAG, "onStartCommand: ");
        startTimer();
        return START_STICKY;
    }

    public void startTimer()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++)
                {
                    if(callback!=null) {
                        callback.update(i + "");
                    }

                    Log.d(TAG, "run: "+i +"val"+ isServiceRunning());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
