package com.truedev.application.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.truedev.application.CameraActivity;
import com.truedev.application.CameraPreview;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Lakshay on 13-02-2015.
 */
public class CameraItemsFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CameraFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.camera_fragment,container,false);
        rootView.findViewById(R.id.bTakePhoto).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bTakePhoto:
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivity(intent);
                break;
        }
    }
}

