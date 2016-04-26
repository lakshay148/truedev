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
import com.truedev.application.Utils.Constants;
import com.truedev.application.models.AutoSuggestData;
import com.truedev.application.models.AutoSuggestResponse;
import com.truedev.application.retrofit.RetrofitRequest;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Lakshay on 26/12/15.
 */
public class AutoCompleteAdapter extends ArrayAdapter<AutoSuggestData> implements Filterable {

    private ArrayList<AutoSuggestData> mData;
    private String mSuggestPath;
    private HashMap<String, String> mParams;
    private static final String TAG = "AutoCompleteAdapter";
    private Context mContext;

    public AutoCompleteAdapter(Context context, int resource, String suggestPath, HashMap<String, String> params) {
        super(context, resource);
        mData = new ArrayList<>();
        mSuggestPath = suggestPath;
        mContext = context;
        mParams = params;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public AutoSuggestData getItem(int position) {
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
                mParams.put(Constants.QUERY, constraint + "");
                Log.d(TAG, "performFiltering: " + mParams.get(Constants.CITY));
                RetrofitRequest.getSuggestions(mSuggestPath, mParams, new Callback<AutoSuggestResponse>() {
                    @Override
                    public void success(AutoSuggestResponse autoSuggestResponse, Response response) {
                        Log.d(TAG, "success: " + autoSuggestResponse.getData().size());
                        mData = autoSuggestResponse.getData();
                        results.values = autoSuggestResponse.getData();
                        results.count = autoSuggestResponse.getData().size();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d(TAG, "failure: " + error.getLocalizedMessage());
                    }
                });
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.d(TAG, "publishResults: " + constraint);
                if (results != null && results.count > 0) {
                    Log.d(TAG, "publishResults: " + results.count);
                    notifyDataSetChanged();
                } else {
                    Log.d(TAG, "publishResults: results null");
                    notifyDataSetInvalidated();
                }
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                Log.d(TAG, "convertResultToString: resultValue " + ((AutoSuggestData) resultValue).getId() + "convertResultToString: resultValue" + ((AutoSuggestData) resultValue).getValue());
                return resultValue == null ? "" : ((AutoSuggestData) resultValue).getValue();
            }
        };
        return filter;
    }
}
