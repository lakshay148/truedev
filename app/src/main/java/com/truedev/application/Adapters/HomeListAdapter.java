package com.truedev.application.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Lakshay on 25/03/16.
 */
public class HomeListAdapter extends BaseRecyclerAdapter {

    public HomeListAdapter(Context context, ArrayList objects, RecyclerView.ViewHolder holder, BindAdapterListener listener) {
        super(context, objects, holder, listener);
    }
}
