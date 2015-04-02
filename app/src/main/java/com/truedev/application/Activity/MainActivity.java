package com.truedev.application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;
import io.fabric.sdk.android.Fabric;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        findViewById(R.id.bTestSample).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SampleActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.lvItems);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Constants.allItems);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
        listAdapter.notifyDataSetChanged();


        // Parse push notification test starts here
//        Parse.initialize(this, "mKNrAWX6jpWUZSdL9Jl5XtQt4fu0HqdYe7x48y4M", "Fsp1iVLgnfI2v2vzHo8raSPwyvr6MKGb3GWVzq6C");
//        ParsePush.subscribeInBackground("", new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
//                } else {
//                    Log.e("com.parse.push", "failed to subscribe for push", e);
//                }
//            }
//        });
        // Parse push notification test ends here
//        throw new RuntimeException("Crashssss");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("Clicked Position" , ""+position);
//        Toast.makeText(getApplicationContext(),"Clicked :"+Constants.allItems[position],Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ItemActivity.class);
        intent.putExtra(Constants.OPEN_FRAGMENT,Constants.allItems[position]);
        startActivity(intent);
    }
}
