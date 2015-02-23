package com.truedev.application;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;


public class GalleryActivity extends ActionBarActivity {

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        File paths = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        Log.e(TAG,"External Path :" + paths.toString());
        Log.e(TAG,"External Storage Directory : "+ Environment.getExternalStorageDirectory().getPath());
        Log.e(TAG,"DCIM Path : "+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) );

        File[] path = getExternalFilesDirs(Environment.DIRECTORY_PICTURES);
        Log.e(TAG , path.length + "length");
        for(File file : path)
        {
            Log.e(TAG, file + "");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
