package com.truedev.application.Gcm;

import com.google.android.gms.iid.InstanceIDListenerService;
import com.mml.app.Utils.Constants;
import com.mml.app.Utils.CustomLog;

/**
 * Created by Lakshay on 04/11/15.
 */
public class CustomInstanceIdListener extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        CustomLog.e(Constants.TAG, "Token Refresh ");

    }
}
