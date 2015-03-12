package com.truedev.application.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.truedev.application.Activity.MapsActivity;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;

/**
 * Created by Lakshay on 20-02-2015.
 */
public class MapsFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private static final String TAG = "MapsFragment";
    private String mapType ="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.maps_layout,container,false);
        rootView.findViewById(R.id.bOpenMap).setOnClickListener(this);
        Spinner sType = (Spinner) rootView.findViewById(R.id.sMapType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,MapsActivity.mapTypes);
        sType.setAdapter(adapter);
        sType.setSelection(1);
        sType.setOnItemSelectedListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bOpenMap:
                Intent intent = new Intent(getActivity(),MapsActivity.class);
                intent.putExtra(Constants.MAP_TYPE,mapType);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG,"Item Selected " + position);
        mapType = MapsActivity.mapTypes[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
