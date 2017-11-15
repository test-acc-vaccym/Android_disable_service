package com.seb.sebastien.appservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sebastien on 11/13/17.
 */

public class SimStateChangedReceiver extends BroadcastReceiver {

    private static final String EXTRA_SIM_STATE = "ss";
    private static final String TAG = "SimStateChangedReceiver";

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
}
