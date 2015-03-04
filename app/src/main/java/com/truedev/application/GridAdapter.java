package com.truedev.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.askerov.dynamicgrid.BaseDynamicGridAdapter;

import java.util.ArrayList;

/**
 * Created by Lakshay on 04-03-2015.
 */
public class GridAdapter extends BaseDynamicGridAdapter{

    private Context context;
    ArrayList<String> names;

    protected GridAdapter(Context context,ArrayList<String> values, int columnCount) {
        super(context,values, columnCount);
        this.context = context;
        this.names = values;
    }

    public class SampleHolder{
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SampleHolder holder ;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_layout,null);
            holder = new SampleHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.ivPhoto);
            holder.textView = (TextView) convertView.findViewById(R.id.tvName);

            convertView.setTag(holder);
        }
        holder = (SampleHolder) convertView.getTag();
        holder.textView.setText("Name :: "+ position);
        holder.imageView.setImageResource(R.drawable.background_image);
        return convertView;
    }
}
