package com.seb.sebastien.appservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SimNotificationService extends Service {

    public static final String EXTRA_SIM_STATE = "ss";
    public static final String SIM = "SIM";
    public SimNotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
