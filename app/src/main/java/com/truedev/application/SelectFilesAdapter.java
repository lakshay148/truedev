package com.truedev.application;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Lakshay on 02-03-2015.
 */
public class SelectFilesAdapter extends BaseAdapter implements View.OnClickListener{

    private static final String TAG = "SelectFilesAdapter";
    private ArrayList<FileInfo> files;
    private Context context;
    private UpdateSelection updateSelection;

    public interface UpdateSelection{
        public void updateSelected(FileInfo fileInfo, Boolean selected);
    }

    public SelectFilesAdapter(Context context, ArrayList<FileInfo> files, UpdateSelection updateSelection) {
        this.files = files;
        this.context = context;
        this.updateSelection = updateSelection;
    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.rlSelectFiles:
                FilesHolder holder = (FilesHolder) v.getTag();
                int position = (int) holder.transparentLayer.getTag();
                FileInfo fileInfo = files.get(position);
                if(fileInfo.getSelected())
                {
                    fileInfo.setSelected(false);
                    holder.selectedCheckBox.setChecked(false);
                    holder.transparentLayer.setVisibility(View.INVISIBLE);
                    this.updateSelection.updateSelected(fileInfo,false);
                }
                else
                {
                    fileInfo.setSelected(true);
                    holder.transparentLayer.setVisibility(View.VISIBLE);
                    holder.selectedImage.setBackgroundColor(context.getResources().getColor(R.color.transparent_white));
                    holder.selectedCheckBox.setChecked(true);
                    this.updateSelection.updateSelected(fileInfo,true);
                }
                break;
        }

    }

    public class FilesHolder
    {
        ImageView selectedImage;
        CheckBox selectedCheckBox;
        View transparentLayer;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FilesHolder filesHolder;

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.select_files,null);
            filesHolder = new FilesHolder();
            filesHolder.selectedImage = (ImageView) convertView.findViewById(R.id.imageSelected);
            filesHolder.selectedCheckBox = (CheckBox) convertView.findViewById(R.id.cbSelectedImage);
            filesHolder.transparentLayer = convertView.findViewById(R.id.vTransparentLayer);
            convertView.setTag( filesHolder);
        }

        filesHolder = (FilesHolder) convertView.getTag();
        FileInfo fileInfo = files.get(position);
        convertView.setOnClickListener(this);
        filesHolder.transparentLayer.setTag(position);
        Glide.with(context).load(fileInfo.getFilePath())
                .placeholder(R.drawable.image_load_default_big)
                .fitCenter()
                .into(filesHolder.selectedImage);

        Log.e(TAG, fileInfo.getDisplayName());
        if(fileInfo != null)
        {
            if(fileInfo.getSelected())
            {
                filesHolder.transparentLayer.setVisibility(View.VISIBLE);
                filesHolder.selectedCheckBox.setChecked(true);
            }
            else
            {
                filesHolder.transparentLayer.setVisibility(View.INVISIBLE);
                filesHolder.selectedCheckBox.setChecked(false);
            }
        }
        return convertView;
    }
}
