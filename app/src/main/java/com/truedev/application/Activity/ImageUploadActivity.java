package com.truedev.application.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.truedev.application.Adapters.PhotosGridAdapter;
import com.truedev.application.ApplicationController;
import com.truedev.application.FileInfo;
import com.truedev.application.Interfaces.UpdateSelection;
import com.truedev.application.R;

import org.askerov.dynamicgrid.DynamicGridView;

/**
 * Created by Lakshay on 13-03-2015.
 */
public class ImageUploadActivity extends Activity implements View.OnClickListener, UpdateSelection , AdapterView.OnItemLongClickListener{

    DynamicGridView gvPhotos;
    PhotosGridAdapter photosGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_fragment);
        findViewById(R.id.bTakePhoto).setOnClickListener(this);
        findViewById(R.id.bFromGallery).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    private void setUpPhotosGrid(View rootView) {

        gvPhotos = (DynamicGridView) rootView.findViewById(R.id.gvPhotos);
        photosGridAdapter = new PhotosGridAdapter(this, ApplicationController.selectedImages , 2 , this);
        gvPhotos.setAdapter(photosGridAdapter);
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
    }

    @Override
    public void updateSelected(FileInfo fileInfo, Boolean selected) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return true;
    }
}
