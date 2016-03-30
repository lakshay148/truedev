package com.truedev.application.Service.Specific;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.truedev.application.Service.ImageUploadService;

import java.util.HashMap;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class EvaluatorImageUploadService extends Service {

    private static final String TAG = "EvaluatorImageService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    private ImageUploadService mService;
    private boolean serviceConnected = false;
    private Handler mHandler;

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            ImageUploadService.ImageUploadBinder binder = (ImageUploadService.ImageUploadBinder) service;
            mService = binder.getService();
            serviceConnected = true;
            mService.setmHandler(mHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
            serviceConnected = false;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
//        if (!serviceConnected) {
            Intent intent1 = new Intent(this, ImageUploadService.class);
            bindService(intent1, mServiceConnection, BIND_AUTO_CREATE);
//        }

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case ImageUploadService.IMAGE_UPLOADED:
                        Log.d(TAG, "handleMessage: Image Uploaded");
                        break;

                    case ImageUploadService.IMAGE_FAILED:
                        Log.d(TAG, "handleMessage: Image Failed");
                        break;
                }
            }
        };

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HashMap<String,String> params = new HashMap<String, String>();
                params.put("apikey","U3KqyrewdMuCotTS");
                params.put("output","json");
                params.put("userKey","rKKqoMbX59Hk0texo7na0sTb0aLO1OY");
                params.put("vcc_id","503040");
                params.put("vc_id","420491");
                params.put("tag_id","10");
                params.put("img_type","2");
                params.put("file_name","abc_z.jpg");
                params.put("source","app");
//                mService.uploadImage();
            }
        },2000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null)
            unbindService(mServiceConnection);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
