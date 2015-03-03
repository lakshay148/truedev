package com.truedev.application.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.truedev.application.FileInfo;
import com.truedev.application.R;

import java.util.ArrayList;

/**
 * Created by Lakshay on 20-02-2015.
 */
public class PhotosGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FileInfo> fileInfos;

    public PhotosGridAdapter(Context context,ArrayList<FileInfo> fileInfos) {

        this.context = context;
        this.fileInfos = fileInfos;
    }

    @Override
    public int getCount() {
        return fileInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return fileInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhotosHolder holder;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.display_images,null);
            holder = new PhotosHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.ivImageDisplay);
            convertView.setTag(holder);
        }
        else
        {
            holder = (PhotosHolder) convertView.getTag();
        }
        Glide.with(context).load(fileInfos.get(position).getFilePath())
                .crossFade()
                .placeholder(R.drawable.image_load_default_small)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        return convertView;
    }

    private class PhotosHolder {

        ImageView image;
    }
}
