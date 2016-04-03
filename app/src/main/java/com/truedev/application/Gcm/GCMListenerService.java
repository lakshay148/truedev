package com.truedev.application.Gcm;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;
import com.mml.app.Utils.Constants;
import com.mml.app.Utils.CustomLog;

/**
 * Created by Lakshay on 04/11/15.
 */
public class GCMListenerService extends GcmListenerService {


    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        CustomLog.e(Constants.TAG, "Message Received From : "+ from);
    }
}
