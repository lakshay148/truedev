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
import android.widget.Button;
import android.widget.CalendarView;

import com.truedev.application.R;
import com.truedev.application.models.DateModel;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lakshay on 07/04/16.
 */
public class CalendarDialog extends Dialog implements CalendarView.OnDateChangeListener, TimeDialog.TimeCallback, View.OnClickListener {

    private static final String TAG = "CalendarDialog";

    private boolean dateAndTime;

    private CalendarCallbacks mCalendarCallbacks;

    private int day;
    private int month;
    private int year;
    private Button bOk;

    public interface CalendarCallbacks  {

        public void onDateSelected(DateModel model);
    }

    public CalendarDialog(Context context, CalendarCallbacks callbacks) {
        super(context);
        mCalendarCallbacks = callbacks;
    }

    public boolean isDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(boolean dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_view);
        findViewById(R.id.bOk).setOnClickListener(this);
        findViewById(R.id.tvCancel).setOnClickListener(this);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setMinDate(System.currentTimeMillis() - 1000);
        calendarView.setOnDateChangeListener(this);
        Date date = new Date(calendarView.getDate());
        this.day = date.getDate();
        this.month = date.getMonth();
        this.year = date.getYear();
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        calendarView.setDate(System.currentTimeMillis());
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        Log.e(TAG, "year " + year + " month " + month + " dayOfMonth " + dayOfMonth);

        this.day = dayOfMonth;
        this.month = month+1;
        this.year = year;

    }


    @Override
    public void onTimeSet(int hour, int minute) {
        DateModel model = new DateModel();
        model.setDay(day);
        model.setMonth(month);
        model.setYear(year);
        model.setMin(minute);
        model.setHour(hour);
        mCalendarCallbacks.onDateSelected(model);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bOk:
                if (isDateAndTime()) {
                    TimeDialog dialog = new TimeDialog(getContext(), this);
                    dialog.show();
                } else {
                    DateModel model = new DateModel();
                    model.setDay(day);
                    model.setMonth(month);
                    model.setYear(year);
                    mCalendarCallbacks.onDateSelected(model);
                }
                dismiss();
                break;

            case R.id.tvCancel:
                dismiss();
                break;
        }
    }
}


