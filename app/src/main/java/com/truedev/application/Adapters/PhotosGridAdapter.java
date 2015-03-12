package com.truedev.application.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.truedev.application.FileInfo;
import com.truedev.application.Interfaces.UpdateSelection;
import com.truedev.application.R;

import org.askerov.dynamicgrid.BaseDynamicGridAdapter;

import java.util.ArrayList;

/**
 * Created by Lakshay on 20-02-2015.
 */
public class PhotosGridAdapter extends BaseDynamicGridAdapter implements View.OnClickListener {

    private static final String TAG = "PhotosGridAdapter";
    private Context context;
    private UpdateSelection updateSelection;

    public PhotosGridAdapter(Context context, ArrayList<FileInfo> fileInfos, int columnCount, UpdateSelection updateSelection) {
        super(context, fileInfos, columnCount);
        this.context = context;
        this.updateSelection = updateSelection;
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
            holder.removeImage = (ImageView) convertView.findViewById(R.id.ivRemoveImage);
            convertView.setTag(holder);
        }
        else
        {
            holder = (PhotosHolder) convertView.getTag();
        }
        holder.removeImage.setTag(position);
        holder.removeImage.setOnClickListener(this);

        FileInfo fileInfo = (FileInfo) getItem(position);
        Glide.with(context).load(fileInfo.getFilePath())
                .crossFade()
                .placeholder(R.drawable.background_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        return convertView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivRemoveImage:
                int position = (int) v.getTag();
                Log.e(TAG, "OnClick Remove position " + position);
                FileInfo fileInfo = (FileInfo) getItem(position);
                getItems().remove(position);
                this.updateSelection.updateSelected(fileInfo,false);
                notifyDataSetChanged();
                break;
        }

    }

    private class PhotosHolder {
        ImageView image;
        ImageView removeImage;
    }
}
