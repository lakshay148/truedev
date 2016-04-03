package com.truedev.application.models;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class ListItem implements Serializable {

    private String title;
    private Action mAction;
    private Class<? extends AppCompatActivity> actionClass;
    private ACTION mAction;

    public enum ACTION{ACTIVITY, SERVICE,FRAGMENT};

    public ACTION getmAction() {
        return mAction;
    }

    public void setmAction(ACTION mAction) {
        this.mAction = mAction;
    }

    public ListItem(String title, Action mAction, Class<? extends AppCompatActivity> actionClass) {
        this.title = title;
        this.mAction = mAction;
        this.actionClass = actionClass;
    }

    public enum Action{ACTIVITY,SERVICE}

    public Action getmAction() {
        return mAction;
    }

    public void setmAction(Action mAction) {
        this.mAction = mAction;
    }

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
