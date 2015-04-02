package com.truedev.application;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lakshay on 18-02-2015.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "CameraPreview";
    Camera mCamera ;
    List<Camera.Size> mSupportedPreviewSizes;
    Camera.Size mPreviewSize;
    private SurfaceHolder holder;
    public CameraPreview(Context context,Camera camera) {
        super(context);
        mCamera = camera;
        holder = getHolder();
        holder.addCallback(this);
        Camera.Parameters parameters = mCamera.getParameters();
        mSupportedPreviewSizes  = parameters.getSupportedPreviewSizes();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {

            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {

            Log.e("Exception ", ""+e.getMessage().toString());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = resolveSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        final int height = resolveSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);

        if (mSupportedPreviewSizes != null) {
            mPreviewSize = getOptimalPreviewSize(mSupportedPreviewSizes, width, height);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Log.e("Surface Changed ","Surface cHANged");
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
            Log.e(TAG,e.getMessage());
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
            mCamera.setPreviewDisplay(holder);
            mCamera.setParameters(parameters);
            mCamera.startPreview();

        } catch (Exception e){
            Log.e(TAG,""+e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mCamera.release();
        Log.e("Camera Preview","Surface Destroyed");
    }

    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio=(double)h / w;

        if (sizes == null) return null;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }
}
