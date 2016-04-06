package com.truedev.application.Activity;

import android.os.Bundle;

import com.truedev.application.R;

/**
 * Created by lakshaygirdhar on 4/4/16.
 */
public class SampleActivity extends BaseCollapsingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.sample_activity, frameLayout);
    }
}
