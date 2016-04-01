package com.truedev.application.Activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.truedev.application.R;

/**
 * Created by lakshaygirdhar on 2/4/16.
 */
public class BaseCollapsingActivity extends AppCompatActivity {

    // protected NestedScrollView nestedScrollView;
    protected Toolbar toolbar;
    protected TabLayout tabLayout;
    protected AppBarLayout appBarLayout;
    protected FloatingActionButton fab;
    protected NestedScrollView frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_collapsing);

        frameLayout = (NestedScrollView) findViewById(R.id.main_container);
//        frameLayout.setFillViewport(true);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

