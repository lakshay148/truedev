package com.truedev.application.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.truedev.application.Fragment.CameraItemsFragment;
import com.truedev.application.R;

/**
 * Created by Lakshay on 13-03-2015.
 */
public class SampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_activity);

        CameraItemsFragment fragment = new CameraItemsFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.photoContainer,fragment).commit();
    }
}
