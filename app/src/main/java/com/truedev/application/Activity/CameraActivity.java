package com.truedev.application.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.truedev.application.Adapters.CapturedImagesAdapter;
import com.truedev.application.CameraPreview;
import com.truedev.application.FileInfo;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lakshay on 18-02-2015.
 */
public class CameraActivity extends Activity implements View.OnClickListener,Camera.PictureCallback {

    private static final String TAG = "CameraActivity";
    public static final String CAMERA_IMAGES = "captured_images";
    private Camera camera;
    private ImageView capturedImageView;
    private LinearLayout llCapturedImages;
    private ArrayList<FileInfo> imagesList = new ArrayList<FileInfo>();
    private CapturedImagesAdapter imagesAdapter;
    private ListView lvCaptureImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_items);

        try {
            // attempt to get a Camera instance
            camera = Camera.open();
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
            Log.e("Camera Open Exception", "" + e.getMessage());
        }

        CameraPreview cameraPreview = new CameraPreview(this, camera);
        FrameLayout camera_lLayout = (FrameLayout) findViewById(R.id.camera_preview);
        camera_lLayout.addView(cameraPreview);

        //set the screen layout to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.button_capture).setOnClickListener(this);
        findViewById(R.id.bDone).setOnClickListener(this);
        findViewById(R.id.bBack).setOnClickListener(this);

        capturedImageView = (ImageView) findViewById(R.id.ivCaptured);
        lvCaptureImages = (ListView) findViewById(R.id.lvCapturedImages);
        imagesAdapter = new CapturedImagesAdapter(this, imagesList );
        lvCaptureImages.setAdapter(imagesAdapter);
        imagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_capture:
                camera.takePicture(null, null, this);
                break;

            case R.id.bBack:
                finish();
                break;

            case R.id.bDone:
                Intent intent = new Intent();
                intent.putExtra(CAMERA_IMAGES,imagesAdapter.getImageInfoArrayList());
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    //updates the listview with the photos clicked by the camera
    private void updateCapturedPhotos(File pictureFile) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilePath(pictureFile.getAbsolutePath());
//        imagesList.add(imageInfo);
        imagesAdapter.getImageInfoArrayList().add(fileInfo);
        imagesAdapter.notifyDataSetChanged();
        lvCaptureImages.smoothScrollToPosition(imagesAdapter.getCount()-1);
        camera.startPreview();
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        File pictureFile = Constants.getMediaOutputFile(Constants.TYPE_IMAGE);
        Log.d(TAG, pictureFile.getAbsolutePath().toString() + "");
        if (pictureFile == null) {
            Log.d(TAG, "Error creating media file, check storage permissions: " +
                    "");
            return;
        }
        try
        {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            updateCapturedPhotos(pictureFile);
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }
}
