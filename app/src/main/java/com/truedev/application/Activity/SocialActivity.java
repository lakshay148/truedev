package com.truedev.application.Activity;

import android.os.Bundle;

import com.truedev.application.R;

/**
 * Created by Lakshay on 03/04/16.
 */
public class SocialActivity extends BaseActivity {

    private static final String TAG = "SocialActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.social_activity, mContentFrame);
    }
}
