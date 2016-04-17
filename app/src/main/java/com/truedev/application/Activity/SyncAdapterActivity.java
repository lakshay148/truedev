package com.truedev.application.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.truedev.application.R;
import com.truedev.application.databinding.GeneralLayoutBinding;
import com.truedev.application.models.GeneralModel;

/**
 * HTD :
 *
 * 1. Copy syncadapter package
 * 2. Copy xml folder in layouts
 * 3. AndroidManifest Permissions
 <uses-permission android:name="android.permission.AUTHENTICATE_ACCOUNTS" />
 <uses-permission android:name="android.permission.WRITE_SYNC_SETTINGS" />

 * 4. Build.gradle changes
 buildConfigField "long", "SYNC_FREQUENCY", "24*60*60"
 resValue("string", "account_type", defaultConfig.applicationId+".staging.account")
 resValue("string", "content_authority", defaultConfig.applicationId+".staging.SyncAdapter.provider")
    5. Provider

 *
 * Created by lakshaygirdhar on 13/4/16.
 */
public class SyncAdapterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneralLayoutBinding binding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.general_layout,null,false);
        GeneralModel user = new GeneralModel("lakshay test binding");
        binding.setUser(user);
//        getLayoutInflater().inflate(R.layout.general_layout,mContentFrame);
    }
}
