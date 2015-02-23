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
import android.widget.GridView;

import com.truedev.application.Adapters.PhotosGridAdapter;
import com.truedev.application.CameraActivity;
import com.truedev.application.CameraPreview;
import com.truedev.application.GalleryActivity;
import com.truedev.application.ImageInfo;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lakshay on 13-02-2015.
 */
public class CameraItemsFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CameraFragment";
    private static final int CODE_CAMERA = 148;
    ArrayList<ImageInfo> imageInfos = new ArrayList<ImageInfo>();
    GridView gvPhotos;
    PhotosGridAdapter photosGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.camera_fragment,container,false);
        rootView.findViewById(R.id.bTakePhoto).setOnClickListener(this);
        rootView.findViewById(R.id.bFromGallery).setOnClickListener(this);
        gvPhotos = (GridView) rootView.findViewById(R.id.gvPhotos);
        photosGridAdapter = new PhotosGridAdapter(getActivity(),imageInfos);
        gvPhotos.setAdapter(photosGridAdapter);
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode)
        {
            case CODE_CAMERA:
                if(data!=null)
                {
                    Log.e("data Activity Result","Not null");
                    ArrayList<ImageInfo> list = (ArrayList<ImageInfo>) data.getSerializableExtra(CameraActivity.CAMERA_IMAGES);
                    imageInfos.addAll(list);
                    Log.e(TAG, list.toString());
                    photosGridAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bTakePhoto:
                Intent intent = new Intent(getActivity(), CameraActivity.class);
                startActivityForResult(intent, CODE_CAMERA);
                break;

            case R.id.bFromGallery:
                Intent intent1 = new Intent(getActivity(), GalleryActivity.class);
                startActivity(intent1);
                break;
        }
    }
}

