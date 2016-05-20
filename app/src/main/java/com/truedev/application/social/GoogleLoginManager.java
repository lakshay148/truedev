package com.truedev.application.social;

import android.app.Activity;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.truedev.application.ApplicationController;
import com.truedev.application.Utils.Constants;
import com.truedev.application.Utils.PrefsUtils;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Lakshay on 17/04/16.
 */

public class GoogleLoginManager implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<People.LoadPeopleResult> {

    private static final String TAG = "GoogleLoginManager";
    private static GoogleLoginManager mInstance;
    private Activity activityContext;

    public static final int GOOGLE_LOGIN_STATUS_SIGNED_IN = 1;
    public static final int GOOGLE_LOGIN_STATUS_SIGNED_OUT = 2;
    public static final int GOOGLE_LOGIN_STATUS_SIGNING_IN = 3;
    public static final int GOOGLE_LOGIN_STATUS_ERROR = 4;

    public static final String USER_EMAIL = "user_email";
    public static final String USER_NAME = "user_email";

    /**
     * Request code used to invoke sign in user interactions.
     **/
    public static final int GOOGLE_SIGN_IN = 0;

    /**
     * Client used to interact with Google APIs.
     **/
    private GoogleApiClient mGoogleApiClient;

    /**
     * Is there a ConnectionResult resolution in progress?
     **/
    private boolean mIsResolving = false;

    /**
     * Should we automatically resolve ConnectionResults when possible?
     **/
    private boolean mShouldResolve = false;

    private HashSet<GoogleLoginCallback> loginStatusListeners = new HashSet<>();
    private HashMap<String, String> loggedInUserInfo = new HashMap<>();

    public static GoogleLoginManager getInstance() {
        if (mInstance == null) {
            synchronized (GoogleLoginManager.class) {
                if (mInstance == null) {
                    mInstance = new GoogleLoginManager();
                }
            }
        }
        return mInstance;
    }

    private GoogleLoginManager() {
        mGoogleApiClient = new GoogleApiClient.Builder(ApplicationController.getInstance().getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        mGoogleApiClient.connect();
    }

    public void login(Activity activityContext, GoogleLoginCallback listener) {
        loginStatusListeners.add(listener);
        if (!mGoogleApiClient.isConnected()) {
            notifyLoginState(GOOGLE_LOGIN_STATUS_SIGNING_IN);
            if(mGoogleApiClient.isConnecting()){
                return;
            }
            this.activityContext = activityContext;
            mShouldResolve = true;
            mGoogleApiClient.connect();
        }else{
            notifyLoginState(GOOGLE_LOGIN_STATUS_SIGNED_IN);
        }
    }

    public void logout() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }
    }

    public boolean getLoginStatus() {
        return mGoogleApiClient.isConnected();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected: ");
        mShouldResolve = false;
        String accountEmail = Plus.AccountApi.getAccountName(mGoogleApiClient);
        loggedInUserInfo.put(USER_EMAIL, accountEmail);
        Plus.PeopleApi.loadVisible(mGoogleApiClient, null).setResultCallback(this);
        Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        if (currentPerson != null) {
            Log.d(TAG, "onConnected: Name "+ currentPerson.getDisplayName());
            Log.d(TAG, "onConnected: Email "+ accountEmail);
            loggedInUserInfo.put(USER_NAME, currentPerson.getDisplayName());
            Toast.makeText(ApplicationController.getInstance().getContext(),currentPerson.getDisplayName(),Toast.LENGTH_SHORT).show();
            PrefsUtils.setStringSharedPreference(ApplicationController.getInstance().getContext(), Constants.USERNAME, currentPerson.getDisplayName());
            PrefsUtils.setStringSharedPreference(ApplicationController.getInstance().getContext(), Constants.EMAIL, accountEmail);
        }
        notifyLoggedInUserInfo();
        notifyLoginState(GOOGLE_LOGIN_STATUS_SIGNED_IN);
    }

    @Override
    public void onConnectionSuspended(int i) {
        //mGoogleApiClient.connect();
        Log.d(TAG, "onConnectionSuspended: ");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(activityContext, GOOGLE_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                activityContext = null;
                notifyError(connectionResult);
                notifyLoginState(GOOGLE_LOGIN_STATUS_ERROR);
            }
        } else {
            activityContext = null;
            notifyLoginState(GOOGLE_LOGIN_STATUS_SIGNED_OUT);
        }
    }

    @Override
    public void onResult(People.LoadPeopleResult loadPeopleResult) {

    }

    public void onActivityResult(int requestCode, int resultCode) {
        if (requestCode == GoogleLoginManager.GOOGLE_SIGN_IN) {
            if (resultCode != Activity.RESULT_OK) {
                mShouldResolve = false;
            }
            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    private void notifyLoginState(int loginState) {
        for (GoogleLoginCallback listener : loginStatusListeners) {
            listener.loginStatusUpdate(loginState);
        }

        if (loginState != GOOGLE_LOGIN_STATUS_SIGNING_IN) {
            loginStatusListeners.clear();
        }
    }

    private void notifyError(ConnectionResult connectionResult) {
        for (GoogleLoginCallback listener : loginStatusListeners) {
            listener.signInError(connectionResult);
        }
    }

    private void notifyLoggedInUserInfo() {
        for (GoogleLoginCallback listener : loginStatusListeners) {
            listener.loggedInUserInfo(loggedInUserInfo);
        }
    }
}

