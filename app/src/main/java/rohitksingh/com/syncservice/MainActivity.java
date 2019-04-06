package rohitksingh.com.syncservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rohitksingh.com.syncservice.Sync.SyncActivity;

public class MainActivity extends SyncActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(getIntent());
            }
        });

    }


    @Override
    public void syncActivity()
    {
        Toast.makeText(this, syncService.isServiceRunning()+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Intent getIntent() {
        Intent intent = new Intent(this, TimerService.class);
        return intent;
    }

    @Override
    public void update(Object object) {

        final String value = (String)object;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.setText(value);
            }
        });

    }
}
