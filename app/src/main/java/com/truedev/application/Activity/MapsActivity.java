package com.truedev.application.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;

/**
 * How To Do :
 *
 *  1. Create a google project and enable the google maps for android api
 *  2. Generate an android key by adding sha-1 of your machine and adding package name com.truedev.application
 *  3. In androidManifest put following metadata with the key generated in the 2nd point
 *

 <meta-data
 android:name="com.google.android.maps.v2.API_KEY"
 android:value="AIzaSyBCnuONgR6ASm4J5nWo2eIL5rf84G14a4U" />

    4. Implement onMapReadyCallback wherever needed
 */

/**
 * Created by Lakshay on 20-02-2015.
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private static final String TAG = "MapsActivity";
    public static final String TERRAIN = "Terrain";
    public static final String HYBRID = "Hybrid";
    public static final String NORMAL = "Normal";
    public static final String NONE = "None";
    public static String[] mapTypes = new String[]{MapsActivity.TERRAIN,MapsActivity.NORMAL,MapsActivity.HYBRID,MapsActivity.NONE};
    private String mapType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        if(getIntent().getExtras() == null)
            mapType = NORMAL;
        else
            mapType = getIntent().getExtras().getString(Constants.MAP_TYPE);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e(TAG,"OnMapReady");
        googleMap.addMarker(new MarkerOptions().position(new LatLng(38.000,77.000)).title("Indiaaaaa "));
        googleMap.setMapType(getMapType(mapType));
    }

    private int getMapType(String mapType) {

        switch (mapType)
        {
            case TERRAIN:
                return GoogleMap.MAP_TYPE_TERRAIN;
            case NORMAL:
                return GoogleMap.MAP_TYPE_NORMAL;
            case HYBRID:
                return GoogleMap.MAP_TYPE_HYBRID;
            default:
                return GoogleMap.MAP_TYPE_NONE;
        }
    }
}
