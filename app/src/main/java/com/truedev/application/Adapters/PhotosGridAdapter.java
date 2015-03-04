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

import org.askerov.dynamicgrid.BaseDynamicGridAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lakshay on 20-02-2015.
 */
public class PhotosGridAdapter extends BaseDynamicGridAdapter implements View.OnClickListener {

    private Context context;
    private ArrayList<FileInfo> fileInfos;

    public PhotosGridAdapter(Context context, ArrayList<FileInfo> fileInfos, int columnCount) {
        super(context, fileInfos, columnCount);
        this.fileInfos = fileInfos;
        this.context = context;
    }


//    public PhotosGridAdapter(Context context,ArrayList<FileInfo> fileInfos) {
//        this.context = context;
//        this.fileInfos = fileInfos;
//    }

//    @Override
//    public int getCount() {
//        return fileInfos.size();
//    }

//    @Override
//    public Object getItem(int position) {
//        return fileInfos.get(position);
//    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        PhotosHolder holder;
//        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.display_images,null);
//            holder = new PhotosHolder();
//            holder.image = (ImageView) convertView.findViewById(R.id.ivImageDisplay);
//            holder.removeImage = (ImageView) convertView.findViewById(R.id.ivRemoveImage);
//            convertView.setTag(holder);
        }
//        else
//        {
//            holder = (PhotosHolder) convertView.getTag();
//        }
//        holder.removeImage.setTag(position);
//        holder.removeImage.setOnClickListener(this);

        Glide.with(context).load(fileInfos.get(position).getFilePath())
                .crossFade()
                .placeholder(R.drawable.background_image)
                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) convertView.findViewById(R.id.ivImageDisplay));
        return convertView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.ivRemoveImage:
                int position = (int) v.getTag();
//                fileInfos.remove(position);
                this.remove(position);
                notifyDataSetChanged();
                break;
        }

    }

    private class PhotosHolder {

        ImageView image;
        ImageView removeImage;
    }
}
