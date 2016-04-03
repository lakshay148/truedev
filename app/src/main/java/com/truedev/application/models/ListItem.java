package com.truedev.application.models;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class ListItem implements Serializable {

    private String title;
    private Class<? extends AppCompatActivity> actionClass;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<? extends AppCompatActivity> getActionClass() {
        return actionClass;
    }

    public void setActionClass(Class<? extends AppCompatActivity> actionClass) {
        this.actionClass = actionClass;
    }
}
