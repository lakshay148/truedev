package com.truedev.application.Gcm;

import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Lakshay on 04/11/15.
 */
public class CustomInstanceIdListener extends InstanceIDListenerService {

    private static final String TAG = "CustomInstanceIdListnr";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.e(TAG, "Token Refresh ");

    }
}
