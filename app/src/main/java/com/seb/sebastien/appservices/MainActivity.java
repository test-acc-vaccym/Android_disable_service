package com.seb.sebastien.appservices;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public boolean Enabling = false;

    private static final String EXTRA_SIM_STATE = "ss";
    private String type_sim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ToggleButton serviceToggleButton = (ToggleButton) findViewById(R.id.serviceOnOff);

        serviceToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Log.d(TAG,"Service is enabled");
                    registerAllReceivers();
                    FirebaseMessaging.getInstance().subscribeToTopic("service_update");

                    // enablingService(true);
                } else {
                    Log.d(TAG,"Service is disabled");
                  //  enablingService(false);
                    unRegisterAllReceivers();
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("service_update");
                }
            }
        });
        handleAction(getCarrier());

    }

    @Override
    protected void onDestroy() {
        unRegisterAllReceivers();
        super.onDestroy();
    }

    private String getCarrier() {
        TelephonyManager manager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        try {
            type_sim = manager.getNetworkOperatorName();
            Log.d(TAG, "Carrier detected : " + type_sim);
        } catch (SecurityException e) {
            Log.e(TAG, "Security Exception...");
        }

        return type_sim;
    }
    /* Firebase and SIM */
    private BroadcastReceiver serviceBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Broadcast received");
            if (intent.getExtras().getString(appFirebaseMessagingService.FIREBASE).equals(appFirebaseMessagingService.FIREBASE))
                type_sim = intent.getExtras().getString(appFirebaseMessagingService.SIM_TYPE);
            else if(intent.getExtras().getString(SimNotificationService.EXTRA_SIM_STATE) != null)
                Log.d(TAG, "Get something from Sim");
            else
                type_sim = null;

            if(type_sim != null){
                handleAction(type_sim);
            }
        }
    };

    private void handleAction(String type_of_sim){
        Log.d(TAG, "THIS IS MY CARRIER: " + type_of_sim);
    }


    private void registerAllReceivers(){
        Log.d(TAG, "Broadcast receiver register");
        registerReceiver(serviceBroadcastReceiver, new IntentFilter(appFirebaseMessagingService.INTENT_FILTER));
    }

    private void unRegisterAllReceivers() {
        if (serviceBroadcastReceiver != null) {
            unregisterReceiver(serviceBroadcastReceiver);
            serviceBroadcastReceiver = null;
        }
    }


}