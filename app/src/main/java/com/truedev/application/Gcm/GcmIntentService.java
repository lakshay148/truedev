package com.truedev.application.Gcm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.truedev.application.Utils.PrefsUtils;

import java.io.IOException;

/**
 * Created by Lakshay on 04/11/15.
 */
public class GcmIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */

    private static final String TAG = "GcmIntentService";
    private static final String GCM_SENDER_ID = "Your GCM Sender ID Here";
    public static final String GCM_ID = "gcmId";

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
            String token = instanceID.getToken(GCM_SENDER_ID,
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Log.e(TAG, "Token Received " + token);
            PrefsUtils.setStringSharedPreference(this, GCM_ID, token);

            //Send Gcm Token
//            RetrofitRequest.sendGcmToken(token);
        } catch (IOException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
}
