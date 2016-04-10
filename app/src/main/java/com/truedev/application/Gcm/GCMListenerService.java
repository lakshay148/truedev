package com.truedev.application.Gcm;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.truedev.application.Activity.HomeActivity;
import com.truedev.application.R;

/**
 * Created by Lakshay on 04/11/15.
 */
public class GCMListenerService extends GcmListenerService {

    private static final String TAG = "GCMListenerService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);

        Log.e(TAG, "Message Received From : " + from);
        String title = data.getString("title") != null ? data.getString("title"):getApplicationContext().getString(R.string.app_name);
        String message = data.getString("message") != null ? data.getString("message"):"Test Gcm Message";
        String activity = data.getString("activity") != null ? data.getString("activity"):"1";

        NotificationUtils.generateNotificationBig(this, title, message, NotificationUtils.getLandingMap(activity), HomeActivity.class);
    }
}
