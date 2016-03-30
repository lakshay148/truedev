package com.truedev.application.retrofit;

import com.truedev.application.NetworkManager.NetworkManager;

import java.util.concurrent.Executors;

import retrofit.RestAdapter;
import retrofit.android.MainThreadExecutor;
import retrofit.client.OkClient;

public class RetrofitAdapter {
    private static final String TAG = "RetrofitAdapter";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static RestAdapter getRestAdapter() {

        return new RestAdapter.Builder()
                .setEndpoint("http://beta.inspection.gaadi.com")
                .setExecutors(Executors.newFixedThreadPool(NetworkManager.CONNECTION_POOL_SIZE), new MainThreadExecutor())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient())
                .build();
    }

}
