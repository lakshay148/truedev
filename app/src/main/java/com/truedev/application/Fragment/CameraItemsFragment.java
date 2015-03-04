package com.truedev.application.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.truedev.application.Adapters.PhotosGridAdapter;
import com.truedev.application.CameraActivity;
import com.truedev.application.FileInfo;
import com.truedev.application.GalleryActivity;
import com.truedev.application.R;

import org.askerov.dynamicgrid.DynamicGridView;

import java.util.ArrayList;

/**
 * Created by Lakshay on 13-02-2015.
 */
public class CameraItemsFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    private static final String TAG = "CameraFragment";
    private static final int CODE_CAMERA = 148;
    private static final int CODE_GALLERY = 256;
    ArrayList<FileInfo> allFileInfos = new ArrayList<FileInfo>();
    DynamicGridView gvPhotos;
    PhotosGridAdapter photosGridAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.camera_fragment,container,false);
        rootView.findViewById(R.id.bTakePhoto).setOnClickListener(this);
        rootView.findViewById(R.id.bFromGallery).setOnClickListener(this);
        gvPhotos = (DynamicGridView) rootView.findViewById(R.id.gvPhotos);
        photosGridAdapter = new PhotosGridAdapter(getActivity(),allFileInfos,2);
        gvPhotos.setAdapter(photosGridAdapter);
//        gvPhotos.setEditModeEnabled(true);
        gvPhotos.setOnItemLongClickListener(this);
        gvPhotos.setOnDropListener(new DynamicGridView.OnDropListener() {
            @Override
            public void onActionDrop() {
                gvPhotos.stopEditMode();
            }
        });
        gvPhotos.setOnDragListener(new DynamicGridView.OnDragListener() {
            @Override
            public void onDragStarted(int position) {

            }

            @Override
            public void onDragPositionsChanged(int oldPosition, int newPosition) {

            }
        });
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
                    ArrayList<FileInfo> list = (ArrayList<FileInfo>) data.getSerializableExtra(CameraActivity.CAMERA_IMAGES);
                    allFileInfos.addAll(list);
                    Log.e(TAG, list.toString());
                    photosGridAdapter.notifyDataSetChanged();
                }
                break;

            case CODE_GALLERY:
                if(data != null)
                {
                    Log.e("data Activity Result","Not null");
                    ArrayList<FileInfo> list = (ArrayList<FileInfo>) data.getSerializableExtra(GalleryActivity.GALLERY_SELECTED_PHOTOS);
                    allFileInfos.addAll(list);
                    Log.e(TAG, list.toString());
                    photosGridAdapter.add(list);
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
                startActivityForResult(intent1, CODE_GALLERY);
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        gvPhotos.startEditMode(position);
        return true;
    }
}

