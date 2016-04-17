package com.truedev.application.Interfaces;

import android.support.v4.app.Fragment;

/**
 * Created by lakshaygirdhar on 7/4/16.
 */
public interface FragmentActionsListener {
    public void onActionDone(Object object, Fragment fragment);
    public void onActionCompleted(Object object, Fragment fragment);
}
