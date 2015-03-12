package com.truedev.application.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;

/**
 * Created by Lakshay on 20-02-2015.
 */
public class MapsActivity extends Activity implements OnMapReadyCallback{

    private static final String TAG = "MapsActivity";
    public static final String TERRAIN = "Terrain";
    public static final String HYBRID = "Hybrid";
    public static final String NORMAL = "Normal";
    public static final String NONE = "None";
    public static String[] mapTypes = new String[]{MapsActivity.TERRAIN,MapsActivity.NORMAL,MapsActivity.HYBRID,MapsActivity.NONE};
    String mapType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

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
