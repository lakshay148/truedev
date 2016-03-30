package com.truedev.application.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class CommonUtils {

    /**
     * @param context
     * @return the internet state
     */
    public static boolean isNetWorkAvailable(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            if (connMgr == null) {
                return false;
            } else if (connMgr.getActiveNetworkInfo() != null
                    && connMgr.getActiveNetworkInfo().isAvailable()
                    && connMgr.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {

                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
