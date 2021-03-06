package rohitksingh.com.syncservice.Sync;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class SyncService extends Service{

    private int service_counter;

    public IBinder serviceBinder = new ServiceBinder();

    public SyncCallback callback;

    @Override
    public void onCreate()
    {
        service_counter =0;
        Log.d("TimerService", "Service onCreate: SyncService");
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int flagId){

        service_counter++;
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serviceBinder;
    }

    public boolean isServiceRunning()
    {
        return service_counter == 0 ? false : true;
    }

    public void registerCallback(SyncCallback callback)
    {
        this.callback = callback;
    }


    public class ServiceBinder extends Binder{

        public SyncService getService()
        {
            return SyncService.this;
        }
    }



}
