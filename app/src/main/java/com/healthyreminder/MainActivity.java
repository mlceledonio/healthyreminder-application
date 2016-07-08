package com.healthyreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mStartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TIMER", "OnCreate");
        setContentView(R.layout.activity_main);

        mStartButton = (Button) findViewById(R.id.button_start);
        final Intent in = new Intent(this, CountdownService.class);

        mStartButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startService(in);
            }
        });
    }


    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            context.stopService(new Intent(context,CountdownService.class));
            Log.d("TIMER", "Service done");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TIMER", "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TIMER", "OnResume");
        registerReceiver(br, new IntentFilter(CountdownService.COUNT_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TIMER", "OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TIMER", "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TIMER", "OnDestroy");
    }
}
