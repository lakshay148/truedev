package com.truedev.application.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.truedev.application.ImageManager.ImageUtils;
import com.truedev.application.NetworkManager.NetworkManager;
import com.truedev.application.Utils.CommonUtils;
import com.truedev.application.models.GeneralResponse;

import org.json.JSONObject;

import java.io.EOFException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class ImageUploadService extends IntentService {

    private static final String TAG = "ImageUploadService";
    public static String IMAGE_PATH = "imagePath";
    public static String IMAGE_PARAMS = "imageParams";
    private int DESIRED_WIDTH = 1024;
    public static final int IMAGE_UPLOADED = 1;
    public static final int IMAGE_FAILED = 2;
    public static final int IMAGE_ERROR = 3;
    private int DESIRED_HEIGHT = 900;
    private HashMap<String, String> params;
    private ImageUploadBinder imageUploadBinder = new ImageUploadBinder();
    private Handler mHandler;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public ImageUploadService() {
        super("imageuploadservice");
    }


    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }


    public void setDesiredWidth(int DESIRED_WIDTH) {
        this.DESIRED_WIDTH = DESIRED_WIDTH;
    }

    public void setDesiredHeight(int DESIRED_HEIGHT) {
        this.DESIRED_HEIGHT = DESIRED_HEIGHT;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (CommonUtils.isNetWorkAvailable(getBaseContext())) {
            Bundle extras = intent.getExtras();
            String imagePath = extras.getString(IMAGE_PATH);
            params = (HashMap<String, String>) extras.getSerializable(IMAGE_PARAMS);
            uploadImage(imagePath, params);
        } else {
            //// TODO: 22/3/16 Internet not working
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return imageUploadBinder;
    }

    public void uploadImage(String imagePath, HashMap<String, String> params) {
        if (imagePath == null)
            if (mHandler != null) {
                Message message = Message.obtain(null, IMAGE_ERROR);
                mHandler.sendMessage(message);
            }
        try {
            requestForImageUpload(imagePath, createRequest(params));
        } catch (EOFException e) {
            Log.d(TAG, "onHandleIntent: " + e.getLocalizedMessage());
        }
    }

    private void requestForImageUpload(final String imagePath, final String params) throws EOFException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GeneralResponse response = NetworkManager.makeImageUploadRequest(ImageUtils.compressImage(imagePath, DESIRED_WIDTH, DESIRED_HEIGHT), params);
                if (response != null && NetworkManager.SUCCESS.equals(response.getStatus())) {
                    Log.d(TAG, "requestForImageUpload: " + response.getMessage());
                    if (mHandler != null) {
                        Message message = Message.obtain(null, IMAGE_UPLOADED, response);
                        mHandler.sendMessage(message);
                    }
                }

                if (mHandler != null) {
                    Message message = Message.obtain(null, IMAGE_FAILED, response);
                    mHandler.sendMessage(message);
                }
                Log.d(TAG, "requestForImageUpload: Not Uploaded");
            }
        }).start();
    }

    private String createRequest(HashMap<String, String> params) {
        try {
            JSONObject paramsJson = new JSONObject();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramsJson.put(entry.getKey(), entry.getValue());
            }
            return paramsJson.toString();
        } catch (Exception e) {
            Log.e(TAG, "Exception e : " + e.getStackTrace().toString());
            return null;
        }
    }

    public class ImageUploadBinder extends Binder {
        public ImageUploadService getService() {
            return ImageUploadService.this;
        }
    }
}