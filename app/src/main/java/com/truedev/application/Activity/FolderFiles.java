package com.truedev.application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.truedev.application.ApplicationController;
import com.truedev.application.FileInfo;
import com.truedev.application.Interfaces.UpdateSelection;
import com.truedev.application.R;
import com.truedev.application.Adapters.SelectFilesAdapter;

import java.util.ArrayList;

/**
 * Created by Lakshay on 02-03-2015.
 */
public class FolderFiles extends ActionBarActivity implements View.OnClickListener, UpdateSelection {

    private static final String TAG = "FolderFiles";
    public static final int RESULT_SKIP_FOLDERS = 10;
    public static final String SELECTED_FILES = "selectedFiles";
    android.support.v7.app.ActionBar actionBar;
    ImageView doneButton;
    ArrayList<FileInfo> files;
    SelectFilesAdapter adapter;
    public ArrayList<FileInfo> selectedFiles = new ArrayList<FileInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folder_files);

        setUpActionBar();

        files = (ArrayList<FileInfo>) getIntent().getExtras().getSerializable(GalleryActivity.FILES_IN_FOLDER);
        adapter = new SelectFilesAdapter(this,files , this);
        GridView folderFiles = (GridView) findViewById(R.id.gvFolderPhotos);
        folderFiles.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setUpActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.action_bar_folder_files);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        doneButton = (ImageView) actionBar.getCustomView().findViewById(R.id.bDone);
        doneButton.setOnClickListener(this);

        String folderName = getIntent().getExtras().getString(GalleryActivity.FOLDER_NAME);
        Log.e("Folder Name ", folderName);
        actionBar.setTitle(folderName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bDone:
                Intent intent = new Intent();
//                Log.e(TAG, "Selected Files Size : "+selectedFiles.size());
//                intent.putExtra(SELECTED_FILES, selectedFiles);
                setResult(RESULT_SKIP_FOLDERS, intent);
                finish();
                break;
        }
    }

    @Override
    public void updateSelected(FileInfo fileInfo, Boolean selected) {
        if(selected)
        {
            ApplicationController.selectedImages.add(fileInfo);
        }
        else
        {
            ApplicationController.selectedImages.remove(fileInfo);
            for(FileInfo fileInfo1 : ApplicationController.selectedImages)
            {
                if(fileInfo.getFilePath().equals(fileInfo1.getFilePath()))
                {
                    Log.e(TAG , "Removed File : "+ fileInfo1);
                    ApplicationController.selectedImages.remove(fileInfo1);
                    break;
                }
            }
        }
    }
}
