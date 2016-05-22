package com.truedev.application.NetworkManager;

import android.content.Context;

import com.truedev.application.BuildConfig;
import com.truedev.application.retrofit.RequestInterface;
import com.truedev.application.retrofit.RetrofitRequest;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class NetworkManager<T> {

    private static final String TAG = "NetworkManager";

    public static final String REST_HOST = BuildConfig.URL;
    public static final long CONNECT_TIMEOUT_SECS = 50;
    public static final long WRITE_TIMEOUT_SECS = 50;
    public static final long READ_TIMEOUT_SECS = 50;
    public static final int CONNECTION_POOL_SIZE = 4;
    public static final int CONNECTION_MAX_IDLE_TIME_MS = 3000;
    public static final String ENVIRONMENT = BuildConfig.ENVIRONMENT;
    private Context mContext;
    private NetworkCallback mCallback;
    public Class<T> mClass;
    private int requestCode;

    private RequestInterface retrofitInterface = RetrofitRequest.requestInterface;

    public interface NetworkCallback<T> {
        public void onNetworkResponse(T object,int requestCode);
        public void onNetworkError(Throwable t,int requestCode);
    }

    public static <T> NetworkManager with(NetworkCallback callback,int requestCode) {
        NetworkManager manager = new NetworkManager();
        manager.mCallback = callback;
        manager.requestCode = requestCode;
        return manager;
    }

    public static <T> NetworkManager with(NetworkCallback callback,int requestCode,Class<T> object) {
        NetworkManager manager = new NetworkManager();
        manager.mCallback = callback;
        manager.requestCode = requestCode;
        manager.mClass = object;
        return manager;
    }



//    public void getResults(HashMap<String,String> params){
//        Call<T> call = retrofitInterface.getResults(params);
//        call.enqueue(new Callback<T>() {
//            @Override
//            public void onResponse(Call<T> call, Response<T> response) {
//                Log.d(TAG, "onResponse: ");
//            }
//
//            @Override
//            public void onFailure(Call<T> call, Throwable t) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });
//
//    }
}
