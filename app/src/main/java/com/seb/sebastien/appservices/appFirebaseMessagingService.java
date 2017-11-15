package com.seb.sebastien.appservices;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class appFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "appFirebaseMessagingService";
    public static final String INTENT_FILTER = "INTENT_FILTER";
    public static final String FIREBASE = "firebase";
    public static final String SIM_TYPE = "simtype";

    public appFirebaseMessagingService() {
    }

   /* @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    } */

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String sim = null;

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            sim = remoteMessage.getNotification().getBody();
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        Intent intent = new Intent(INTENT_FILTER);
        intent.putExtra(FIREBASE, FIREBASE);
        intent.putExtra(SIM_TYPE, sim );

        sendBroadcast(intent);

    }
}
