package com.truedev.application.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lakshay on 26/12/15.
 */
public class AutoSuggestResponse implements Serializable {

    ArrayList<AutoSuggestData> data;

    public ArrayList<AutoSuggestData> getData() {
        return data;
    }

    public void setData(ArrayList<AutoSuggestData> data) {
        this.data = data;
    }
}
