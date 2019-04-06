package rohitksingh.com.syncservice.Sync;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public abstract class SyncActivity extends AppCompatActivity implements SyncCallback {


    //TODO
    // Create a utility method
    // If SplashActivity is used then the time will be reduced? how use an AppUtility


    public SyncService syncService;
    public Intent checkServiceRunning;
    private boolean bound = false;

    private static final String TAG = "TimerService";


    @Override
    protected void onStart()
    {
        Log.d(TAG, "onCreate: SyncActivity");
        super.onStart();
        bindService();
    }

    @Override
    public void onStop()
    {
        Log.d(TAG, "onStop: SyncActivity");
        super.onStop();
        unbindService();
    }


    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            bound = true;
            SyncService.ServiceBinder binder = (SyncService.ServiceBinder)service;
            syncService = binder.getService();
            boolean status = syncService.isServiceRunning();
            Log.d(TAG, "onServiceConnected: "+ status);
            syncService.registerCallback(SyncActivity.this);
            syncActivity();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            bound = false;

        }
    };

    public void bindService(){
        Log.d(TAG, "bindService: ");
        checkServiceRunning = getIntent();
        bindService(checkServiceRunning, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    public void unbindService()
    {
        Log.d(TAG, "unbindService: ");
        unbindService(serviceConnection);
    }


    //// Abstract methods

    // To syncActivity state when actvity is ewntered
    public abstract void syncActivity();



}
