package com.truedev.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import java.util.ArrayList;

/**
 * Created by Lakshay on 12-03-2015.
 */
public class ApplicationController extends Application {

    public static String appName = BuildConfig.APPLICATION_NAME;
    public static ArrayList<FileInfo> selectedImages = new ArrayList<FileInfo>();
    private Context mContext;
    private static ApplicationController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mContext = this;
    }

    public static ApplicationController getInstance() {
        return mInstance;
    }

    public Context getContext() {
        return mContext;
    }
}
