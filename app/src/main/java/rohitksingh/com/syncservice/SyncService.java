package rohitksingh.com.syncservice;

import android.app.Service;
import android.content.Intent;

public abstract class SyncService extends Service{

    private int service_counter;

    @Override
    public void onCreate()
    {
        service_counter =0;
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int flagId){

        service_counter++;
        return START_STICKY;
    }

    public boolean isServiceRunning()
    {
        return service_counter == 0 ? false : true;
    }

}
