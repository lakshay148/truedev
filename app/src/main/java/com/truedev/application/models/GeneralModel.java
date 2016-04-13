package com.truedev.application.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 13/4/16.
 */
public class GeneralModel extends BaseObservable implements Serializable {

    public String name;

    public GeneralModel(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
