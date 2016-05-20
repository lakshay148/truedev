package com.truedev.application.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.truedev.application.Adapters.AutoCompleteAdapter;
import com.truedev.application.R;
import com.truedev.application.models.AutoSuggestData;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lakshaygirdhar on 17/5/16.
 */
public class TestActivity extends AppCompatActivity implements AutoCompleteAdapter.FilterResultsListener {

    private static final String TAG = "TestActivity";
    private AutoCompleteTextView actvSample;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.test_activity);

        actvSample = (AutoCompleteTextView) findViewById(R.id.actvSample);
        AutoCompleteAdapter<AutoSuggestData> adapter = new AutoCompleteAdapter<>(this,R.layout.autosuggest, actvSample.getText().toString(),new HashMap<String,String>(),this);
        actvSample.setAdapter(adapter);
    }

    @Override
    public ArrayList filterResults(String sequence) {
        return null;
    }
}
