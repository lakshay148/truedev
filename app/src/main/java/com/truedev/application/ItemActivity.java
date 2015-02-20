package com.truedev.application;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.commonsware.cwac.camera.CameraHost;
import com.commonsware.cwac.camera.CameraHostProvider;
import com.commonsware.cwac.camera.SimpleCameraHost;
import com.truedev.application.Fragment.AnalyticsFragment;
import com.truedev.application.Fragment.CameraItemsFragment;
import com.truedev.application.Fragment.MapsFragment;
import com.truedev.application.Utils.Constants;

import static com.truedev.application.Utils.Constants.OPEN_FRAGMENT;
import com.facebook.Session;

public class ItemActivity extends Activity implements CameraHostProvider{

    private static final String TAG = "ItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        if (savedInstanceState == null) {
            String openFragment = (String) getIntent().getExtras().get(OPEN_FRAGMENT);
            Log.e(TAG,"Fragment to open :: "+ openFragment);
            initializeFragment(openFragment);
        }
    }

    private void initializeFragment(String openFragment) {

        switch (openFragment)
        {
            case Constants.ANALYTICS:
                getFragmentManager().beginTransaction()
                        .add(R.id.container, new AnalyticsFragment()).commit();
                break;

            case Constants.CAMERA_ITEMS:
                getFragmentManager().beginTransaction()
                        .add(R.id.container, new CameraItemsFragment()).commit();
                break;

            case Constants.MAPS:
                Log.e(TAG,"Maps fragment");
                getFragmentManager().beginTransaction().add(R.id.container,new MapsFragment()).commit();
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
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

    @Override
    public CameraHost getCameraHost() {
        SimpleCameraHost cameraHost = new SimpleCameraHost(this);
//        cameraHost.
        return cameraHost;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_item, container, false);
            return rootView;
        }
    }
}
