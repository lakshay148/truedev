package com.truedev.application.retrofit;

import android.os.Build;

import com.squareup.okhttp.OkHttpClient;
import com.truedev.application.ApplicationController;
import com.truedev.application.BuildConfig;
import com.truedev.application.Utils.CommonUtils;
import com.truedev.application.Utils.Constants;
import com.truedev.application.Utils.PrefsUtils;
import com.truedev.application.models.AutoSuggestResponse;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Lakshay on 20/07/15.
 */
public class RetrofitRequest {


    //    public static String url = "http://multiplymyleads.co:8000";
    public static String url = BuildConfig.URL;
    //    private static String url = "http://192.168.0.14:8000";
    private static RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(url)
            .setClient(new OkClient(new OkHttpClient()))
            .setLogLevel(BuildConfig.ENVIRONMENT.equals("DEV") ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
            .setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        request.addHeader(Constants.DEVICE_ID, PrefsUtils.getStringSharedPreference(
                                ApplicationController.getInstance().getContext(), Constants.DEVICE_ID, ""));
                    } else {
                        request.addHeader(Constants.DEVICE_ID, PrefsUtils.getStringSharedPreference(
                                ApplicationController.getInstance().getContext(), Constants.DEVICE_ID, CommonUtils.getDeviceId(ApplicationController.getInstance().getContext())));
                    }
                    request.addHeader(Constants.APP_VERSION_LABEL, Constants.APP_VERSION + "");
                }
            })
            .build();

    public static RequestInterface requestInterface = restAdapter.create(RequestInterface.class);

    public static void getSuggestions(String path, HashMap<String, String> params, Callback<AutoSuggestResponse> callback) {
        requestInterface.getSuggestions(path, params, callback);
    }
}
