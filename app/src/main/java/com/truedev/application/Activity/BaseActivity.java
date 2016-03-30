package com.truedev.application.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.truedev.application.R;

/**
 * Created by lakshaygirdhar on 22/3/16.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    protected FrameLayout mContentFrame;

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mContentFrame = (FrameLayout) findViewById(R.id.contentFrame);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
