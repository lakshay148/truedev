package com.truedev.application.Utils;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.truedev.application.ApplicationController;

/**
 * Created by lakshaygirdhar on 21/4/16.
 */
public class LocationManager implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "LocationManager";
    private static LocationManager mInstance;
    private GoogleApiClient mGoogleApiClient;

    public static LocationManager newInstance() {

        if(mInstance == null)
            mInstance = new LocationManager();
        mInstance.connectGoogle();
        return mInstance;
    }

    public void connectGoogle(){
        if(!mGoogleApiClient.isConnected()){
            mGoogleApiClient.connect();
        }
    }

    private LocationManager(){
        if(mGoogleApiClient == null){
            mGoogleApiClient = new GoogleApiClient.Builder(ApplicationController.getInstance().getContext())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected: ");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: ");
    }
}
