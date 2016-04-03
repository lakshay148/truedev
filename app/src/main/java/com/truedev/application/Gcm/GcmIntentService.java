package com.truedev.application.Gcm;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.mml.app.Utils.Constants;
import com.mml.app.Utils.CustomLog;
import com.mml.app.Utils.PrefsUtils;
import com.mml.app.retrofit.RetrofitRequest;

import java.io.IOException;

/**
 * Created by Lakshay on 04/11/15.
 */
public class GcmIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */

    public GcmIntentService(){
        super("Gcm Service");
    }
    public GcmIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String token = instanceID.getToken(Constants.GCM_SENDER_ID,
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            CustomLog.e(Constants.TAG, "Token Received "+ token);
            PrefsUtils.setStringSharedPreference(this, Constants.GCM_ID, token);
            RetrofitRequest.sendGcmToken(token);
        } catch (IOException e) {
            CustomLog.e(Constants.TAG, e.getLocalizedMessage());
        }
    }
}
