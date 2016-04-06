package com.truedev.application.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.truedev.application.R;
import com.truedev.application.Utils.CalendarDialog;
import com.truedev.application.Utils.TimeDialog;
import com.truedev.application.models.DateModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lakshay on 07/04/16.
 */
public class DateTimeAcitivity extends BaseActivity implements CalendarDialog.CalendarCallbacks, TimeDialog.TimeCallback {

    private static final String TAG = "DateTimeAcitivity";

    @Bind(R.id.bDateDialog)
    Button bDate;

    @Bind(R.id.bTimeDialog)
    Button bTime;

    @OnClick({R.id.bDateDialog, R.id.bTimeDialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bDateDialog:
                CalendarDialog dialog = new CalendarDialog(this, this);
                dialog.show();
                break;
            case R.id.bTimeDialog:
                TimeDialog dialog1 = new TimeDialog(this, this);
                dialog1.show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.date_time_activity, mContentFrame);
        ButterKnife.bind(this);
    }

    @Override
    public void onDateSelected(DateModel model) {
        Log.d(TAG, "onDateSelected: "+ model.getMonth());
    }

    @Override
    public void onTimeSet(int hour, int minute) {
        Log.d(TAG, "onTimeSet: "+ hour);
    }
}
