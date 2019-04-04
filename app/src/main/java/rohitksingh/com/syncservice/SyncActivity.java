package rohitksingh.com.syncservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public abstract class SyncActivity extends AppCompatActivity {


    public SyncService syncService;
    public Intent checkServiceRunning;
    private boolean bound = false;

    private static final String TAG = "SyncActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bindService();
    }

    @Override
    public void onStop()
    {
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
            syncActivity();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            bound = false;

        }
    };

    public void bindService(){
        Log.d(TAG, "bindService: ");
        checkServiceRunning = new Intent(SyncActivity.this, SyncService.class);
        bindService(checkServiceRunning, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    public void unbindService()
    {
        Log.d(TAG, "unbindService: ");
        unbindService(serviceConnection);
    }

    public abstract void syncActivity();


}
