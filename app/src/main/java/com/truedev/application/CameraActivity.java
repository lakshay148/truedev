package com.truedev.application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.truedev.application.Utils.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Lakshay on 18-02-2015.
 */
public class CameraActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "CameraActivity";
    private Camera camera;
    private ImageView capturedImageView;
    private LinearLayout llCapturedImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_items);

        try {
            camera = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            Log.e("Camera Open Exception", "" + e.getMessage());
        }

        capturedImageView = (ImageView) findViewById(R.id.ivCaptured);
        CameraPreview cameraPreview = new CameraPreview(this,camera);
        FrameLayout camera_lLayout = (FrameLayout) findViewById(R.id.camera_preview);
        camera_lLayout.addView(cameraPreview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.button_capture).setOnClickListener(this);
        llCapturedImages = (LinearLayout) findViewById(R.id.llCapturedImages);
        findViewById(R.id.bBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_capture:
                camera.takePicture(null,null,mPicture);
                break;

            case R.id.bBack:
                finish();
                break;
        }
    }

    Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = Constants.getMediaOutputFile(Constants.TYPE_IMAGE);
            Log.d(TAG,pictureFile.getAbsolutePath().toString()+"");
            if (pictureFile == null){
                Log.d(TAG, "Error creating media file, check storage permissions: " +
                        "");
                return;
            }
            try {

                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;

                Bitmap bitmap = BitmapFactory.decodeFile(pictureFile.getAbsolutePath(),options);
                RelativeLayout imageLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.captured_images_overlay,null);
                ImageView imageView = (ImageView)imageLayout.findViewById(R.id.ivCaptured);
                imageView.setImageBitmap(bitmap);
                llCapturedImages.addView(imageLayout);
                camera.startPreview();

            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }
    };
}
