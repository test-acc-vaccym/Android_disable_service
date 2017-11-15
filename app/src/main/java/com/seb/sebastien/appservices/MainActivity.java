package com.seb.sebastien.appservices;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public boolean Enabling = false;
    private ResponseReceiver receiver;

    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "com.seb.sebastien.appservices.intent.action.MESSAGE_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {
            TextView result = (TextView) ((Activity)context).findViewById(R.id.service_status);
            String text = intent.getStringExtra(EnDisAppIntentService.ENABLING_RESULT);
            result.setText(text);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        registerReceiver();

        ToggleButton serviceToggleButton = (ToggleButton) findViewById(R.id.serviceOnOff);

        serviceToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Log.d(TAG,"Service is enabled");
                    enablingService(true);
                } else {
                    Log.d(TAG,"Service is disabled");
                    enablingService(false);

                }
            }
        });

    }

    private void enablingService(boolean enabling){
        Intent msgIntent = new Intent(this, EnDisAppIntentService.class);
        msgIntent.putExtra(EnDisAppIntentService.ENABLING_ACTION, enabling);
        startService(msgIntent);
    }

    private void registerReceiver(){
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
    }
}