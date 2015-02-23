package com.truedev.application.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.truedev.application.ImageInfo;
import com.truedev.application.R;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lakshay on 19-02-2015.
 */

public class CapturedImagesAdapter extends BaseAdapter implements View.OnClickListener {
    Context mContext;
    ArrayList<ImageInfo> imageInfoArrayList;

    public ArrayList<ImageInfo> getImageInfoArrayList() {
        return imageInfoArrayList;
    }

    public void setImageInfoArrayList(ArrayList<ImageInfo> imageInfoArrayList) {
        this.imageInfoArrayList = imageInfoArrayList;
    }

    public CapturedImagesAdapter(Context context, ArrayList<ImageInfo> list) {
        this.mContext = context;
        imageInfoArrayList = list;
    }

    @Override
    public void onClick(View v) {

        int position = (int) v.getTag();
        switch (v.getId())
        {
            case R.id.ivRemoveImage:
                Log.e("On Click Remove",position+"");
                File file = new File(imageInfoArrayList.get(position).getImagePath());
                file.delete();
                imageInfoArrayList.remove(position);
                notifyDataSetChanged();
                break;
        }
    }

    public class ImagesHolder implements Serializable{

        ImageView ivRemoveImage;
        ImageView capturedImage;
    }

    @Override
    public int getCount() {
        return imageInfoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageInfoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return imageInfoArrayList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ImagesHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.captured_images_overlay, null);
            holder = new ImagesHolder();
            holder.capturedImage = (ImageView) convertView.findViewById(R.id.ivCaptured);
            holder.ivRemoveImage = (ImageView) convertView.findViewById(R.id.ivRemoveImage);
            Log.e("Position getView", position + "");
            holder.ivRemoveImage.setTag(position);
            convertView.setTag(holder);
        } else {
            holder = (ImagesHolder) convertView.getTag();
        }

        holder.ivRemoveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("On Click Remove",position+"");
                File file = new File(imageInfoArrayList.get(position).getImagePath());
                file.delete();
                imageInfoArrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        Glide.with(mContext).load("file://" + imageInfoArrayList.get(position).getImagePath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .placeholder(R.drawable.image_load_default_small)
                .into(holder.capturedImage);
        return convertView;
    }
}
