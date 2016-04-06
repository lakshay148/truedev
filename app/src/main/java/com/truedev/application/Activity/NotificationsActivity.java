package com.truedev.application.Activity;

import android.os.Bundle;

import com.truedev.application.R;
/**
 * How To Do :
 *
 * 1. Copy Gcm Package to your application
 * 2. Do manifest changes as following :
 *  Manfifest Changes
   <receiver
 android:name="com.google.android.gms.gcm.GcmReceiver"
 android:exported="true"
 android:permission="com.google.android.c2dm.permission.SEND">
 <intent-filter>
 <action android:name="com.google.android.c2dm.intent.RECEIVE" />
 <action android:name="com.google.android.c2dm.intent.REGISTRATION" />
 <category android:name="com.truedev.application" />
 </intent-filter>
 </receiver>

 <service
 android:name="com.truedev.application.Gcm.GCMListenerService"
 android:exported="false">
 <intent-filter>
 <action android:name="com.google.android.c2dm.intent.RECEIVE" />
 </intent-filter>
 </service>

 <service
 android:name="com.truedev.application.Gcm.CustomInstanceIdListener"
 android:exported="false">
 <intent-filter>
 <action android:name="com.google.android.gms.iid.InstanceID" />
 </intent-filter>
 </service>
 */

/**
 * Created by Lakshay on 03/04/16.
 */
public class NotificationsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.notifications_activity, mContentFrame);
    }
}
