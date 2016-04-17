package com.truedev.application.events;

/**
 * Created by lakshaygirdhar on 11/4/16.
 */
public class CheckChangedEvent extends Object {
    private boolean checked;
    private int position;

    public CheckChangedEvent(boolean checked, int position) {
        this.checked = checked;
        this.position = position;
    }
}
