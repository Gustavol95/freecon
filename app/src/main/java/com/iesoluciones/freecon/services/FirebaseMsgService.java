package com.iesoluciones.freecon.services;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by iedeveloper on 10/08/17.
 */

public class FirebaseMsgService extends FirebaseMessagingService {
    static final String TAG = FirebaseMsgService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG,remoteMessage.getData().get("solicitud_id")+" ESE MERO ES ");
    }



}
