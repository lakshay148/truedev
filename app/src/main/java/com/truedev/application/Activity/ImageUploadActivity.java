package com.truedev.application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.truedev.application.R;
import com.truedev.application.Service.Specific.EvaluatorImageUploadService;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by lakshaygirdhar on 29/3/16.
 */
public class ImageUploadActivity extends BaseActivity {

    private static final String TAG = "ImageUploadActivity";

    @Bind(R.id.bUpload)
    Button bUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.image_upload_layout, mContentFrame);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bUpload:
                Log.d(TAG, "onClick: ");

                Intent intent = new Intent(this,EvaluatorImageUploadService.class);
                startService(intent);
                break;
        }
    }
}
