package com.seb.sebastien.appservices;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;

public class EnDisAppIntentService extends IntentService {
    public static final String ENABLED = "enabled";
    public static final String DISABLED = "disabled";


    public static final String ENABLING_ACTION = "";
    public static final String ENABLING_RESULT = "disabled";

    private static final String EXTRA_SIM_STATE = "ss";
    private static final String TAG = "EnDisAppIntentService";


    public EnDisAppIntentService() {
        super("EnDisAppIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        boolean enabling = intent.getBooleanExtra(ENABLING_ACTION, false);
        if(enabling) {
            serviceStatus(ENABLED);
            enablingEventListener();
        }
        else
            serviceStatus(DISABLED);

    }

    private void enablingEventListener(){
        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String state = intent.getExtras().getString(EXTRA_SIM_STATE);
                if (state == null) {
                    return;
                }
                Log.v(TAG, "Changed detected to " + state);

                switch (state) {
                    case "ABSENT": break;
                    case "NETWORK_LOCKED": break;
                    // etc.
                }
            }
        };
        mReceiver.goAsync();
    }

    private void serviceStatus(String status){
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(MainActivity.ResponseReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(ENABLING_RESULT, status);
        sendBroadcast(broadcastIntent);
    }
}