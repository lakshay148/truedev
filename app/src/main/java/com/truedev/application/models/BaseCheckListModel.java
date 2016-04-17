package com.truedev.application.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lakshaygirdhar on 7/4/16.
 */
public class BaseCheckListModel implements Serializable {

    private String title;
    private ArrayList<CheckListItem> item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<CheckListItem> getItem() {
        return item;
    }

    public void setItem(ArrayList<CheckListItem> item) {
        this.item = item;
    }
}
