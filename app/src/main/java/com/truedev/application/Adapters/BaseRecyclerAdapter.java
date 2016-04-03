package com.truedev.application.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lakshaygirdhar on 22/3/16.
 */
public class BaseRecyclerAdapter<S, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    private Context mContext;
    private ArrayList<S> mObjects;
    private BindAdapterListener mListener;
    private T mHolder;

    public interface BindAdapterListener<T> {
        public void onBind(T holder, int position);
    }

    public BaseRecyclerAdapter(Context context, ArrayList<S> objects, T holder, BindAdapterListener listener) {
        mContext = context;
        mObjects = objects;
        mHolder = holder;
        mListener = listener;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {

        return mHolder;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        mListener.onBind(holder, position);
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }
}
