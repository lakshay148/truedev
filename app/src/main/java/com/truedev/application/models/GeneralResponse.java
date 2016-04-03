package com.truedev.application.models;

import java.io.Serializable;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class GeneralResponse implements Serializable {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
