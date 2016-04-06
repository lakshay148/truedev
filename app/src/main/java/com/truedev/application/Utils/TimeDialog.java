package com.truedev.application.Utils;

/**
 * Created by Lakshay on 07/04/16.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TimePicker;

import com.truedev.application.R;


/**
 * Created by Lakshay on 07/04/16.
 */

public class TimeDialog extends Dialog implements TimePicker.OnTimeChangedListener, View.OnClickListener {

    private static final String TAG = "TimeDialog";
    private TimePicker mTimePicker;
    private TimeCallback mCallback;
    private int hour;
    private int minute;

    public interface TimeCallback  {
        public void onTimeSet(int hour, int minute);
    }

    public TimeDialog(Context context, TimeCallback callback) {
        super(context);
        mCallback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_dialog);

        findViewById(R.id.bOk).setOnClickListener(this);
        findViewById(R.id.tvCancel).setOnClickListener(this);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        mTimePicker.setOnTimeChangedListener(this);
        mTimePicker.setIs24HourView(true);
        this.hour = mTimePicker.getCurrentHour();
        this.minute = mTimePicker.getCurrentMinute();
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        Log.e(TAG, "Hour : " + hourOfDay + " Minute : " + minute);
        this.hour = hourOfDay;
        this.minute = minute;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bOk:
                mCallback.onTimeSet(hour, minute);
                dismiss();
                break;

            case R.id.tvCancel:
                dismiss();
                break;
        }
    }
}


