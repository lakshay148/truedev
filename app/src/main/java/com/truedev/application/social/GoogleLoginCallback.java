package com.truedev.application.social;

import com.google.android.gms.common.ConnectionResult;

import java.util.HashMap;

/**
 * Created by Lakshay on 17/04/16.
 */
public interface GoogleLoginCallback {
    void loginStatusUpdate(int status);
    void signInError(ConnectionResult connectionResult);
    void loggedInUserInfo(HashMap<String, String> info);
}
