package com.truedev.application.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truedev.application.R;

/**
 * Created by Lakshay on 13-02-2015.
 */
public class AnalyticsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.analytics_fragment,container,false);

        return rootView;
    }
}
