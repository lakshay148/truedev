package com.truedev.application.Gcm;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Lakshay on 04/11/15.
 */
public class GCMListenerService extends GcmListenerService {

    private static final String TAG = "GCMListenerService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        Log.e(TAG, "Message Received From : " + from);
    }
}
