package com.truedev.application;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Lakshay on 20-02-2015.
 */
public class MapsActivity extends Activity implements OnMapReadyCallback{

    private static final String TAG = "MapsActivity";
    public static final String TERRAIN = "terrain";
    public static final String HYBRID = "hybrid";
    public static final String NORMAL = "normal";
    public static final String NONE = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e(TAG,"OnMapReady");
        googleMap.addMarker(new MarkerOptions().position(new LatLng(38.000,77.000)).title("Indiaaaaa "));

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
