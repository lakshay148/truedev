package com.truedev.application.models;

import java.io.Serializable;

/**
 * Created by Lakshay on 26/12/15.
 */
public class AutoSuggestData implements Serializable {

    private String id;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
