package com.truedev.application.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;
import com.truedev.application.NetworkManager.NetworkManager;
import com.truedev.application.Utils.Constants;
import com.truedev.application.core.ItemTypeAdapterFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.android.MainThreadExecutor;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class RetrofitAdapter {
    private static final String TAG = "RetrofitAdapter";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static RestAdapter getRestAdapter() {

        final Gson gson = new GsonBuilder().setDateFormat(DATE_FORMAT_PATTERN).registerTypeAdapterFactory(new ItemTypeAdapterFactory()).create();
        final GsonConverter converter = new GsonConverter(gson);
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(Constants.CONNECT_TIMEOUT_SECS, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(Constants.WRITE_TIMEOUT_SECS, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(Constants.READ_TIMEOUT_SECS, TimeUnit.SECONDS);
        okHttpClient.setConnectionPool(new ConnectionPool(Constants.CONNECTION_POOL_SIZE, Constants.CONNECTION_MAX_IDLE_TIME_MS));
        okHttpClient.setRetryOnConnectionFailure(true);

        return new RestAdapter.Builder()
                .setEndpoint("http://beta.inspection.gaadi.com")
                .setExecutors(Executors.newFixedThreadPool(NetworkManager.CONNECTION_POOL_SIZE), new MainThreadExecutor())
                .setConverter(converter)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient))
                .build();
    }
}
