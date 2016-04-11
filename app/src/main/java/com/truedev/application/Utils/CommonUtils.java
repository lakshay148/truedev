package com.truedev.application.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

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

    /**
     *
     * @param recyclerView
     * @param context
     * @param orientation
     * @return
     */
    public static RecyclerView recyclerView(RecyclerView recyclerView, Context context, boolean orientation) {
        LinearLayoutManager recycler_layout = new LinearLayoutManager(context);
        if (orientation)
            recycler_layout.setOrientation(LinearLayout.VERTICAL);
        else
            recycler_layout.setOrientation(LinearLayout.HORIZONTAL);

        recyclerView.setLayoutManager(recycler_layout);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }

}
