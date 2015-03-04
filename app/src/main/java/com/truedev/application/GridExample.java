package com.truedev.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import org.askerov.dynamicgrid.DynamicGridView;

import java.util.ArrayList;

/**
 * Created by Lakshay on 04-03-2015.
 */
public class GridExample extends Activity implements AdapterView.OnItemLongClickListener{

    DynamicGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> values = new ArrayList<String>();
        values.add("Lakjsdha");
        values.add("sadfadsf");
        values.add("afdsfewrr");
        values.add("iuytrwer");
        setContentView(R.layout.gv_example);
        gridView = (DynamicGridView) findViewById(R.id.dGridView);

        GridAdapter adapter = new GridAdapter(this,values, 3);
        gridView.setAdapter(adapter);

        gridView.setOnItemLongClickListener(this);

        gridView.setOnDropListener(new DynamicGridView.OnDropListener() {
            @Override
            public void onActionDrop() {
                gridView.stopEditMode();
            }
        });
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        gridView.startEditMode(position);
        return true;
    }
}
