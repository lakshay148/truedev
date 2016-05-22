package com.truedev.application.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.truedev.application.R;
import com.truedev.application.models.AutoSuggestData;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Lakshay on 26/12/15.
 */
public class AutoCompleteAdapter<T extends AutoSuggestData> extends ArrayAdapter<T> implements Filterable {

    private ArrayList<T> mData;
    private String mSuggestPath;
    private HashMap<String, String> mParams;
    private static final String TAG = "AutoCompleteAdapter";
    private Context mContext;
    private FilterResultsListener mListener;

    public interface   FilterResultsListener<T> {
        public ArrayList<T> filterResults(String sequence);
    }

    /**
     * @param context
     * @param resource    tuple layout to be used for autosuggest UI
     * @param suggestPath
     * @param params
     */
    public AutoCompleteAdapter(Context context, int resource, String suggestPath, HashMap<String, String> params, FilterResultsListener listener) {
        super(context, resource);
        mData = new ArrayList<>();
        mSuggestPath = suggestPath;
        mListener = listener;
        mContext = context;
        mParams = params;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.autosuggest, null);
        ((TextView) convertView.findViewById(R.id.tvName)).setText(mData.get(position).getValue());
        convertView.setTag(mData.get(position).getId());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                final FilterResults results = new FilterResults();
                if (constraint != null) {
                    ArrayList<T> response = mListener.filterResults(constraint.toString());
                    if (response != null) {
                        mData = (ArrayList<T>) response;
                        results.values = response;
                        results.count = response.size();
                        notifyDataSetChanged();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return resultValue == null ? "" : ((T) resultValue).getValue();
            }
        };
        return filter;
    }
}
