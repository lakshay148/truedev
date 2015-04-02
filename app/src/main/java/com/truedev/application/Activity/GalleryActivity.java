package com.truedev.application.Activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.truedev.application.Adapters.ImagesFoldersAdapter;
import com.truedev.application.ApplicationController;
import com.truedev.application.FileInfo;
import com.truedev.application.Fragment.CameraItemsFragment;
import com.truedev.application.R;

import java.io.File;
import java.util.ArrayList;


public class GalleryActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "GalleryActivity";
    public static final String FILES_IN_FOLDER = "folder_files";
    public static final String FOLDER_NAME = "folder_name";
    private static final int REQUEST_FOLDER_FILES = 100;
    public static final String GALLERY_SELECTED_PHOTOS = "galleryPhotos";
    public static final String ALREADY_SELECTED_FILES = "alreadySelectedFiles";
    private ArrayList<FileInfo> folders = new ArrayList<FileInfo>();
    ImagesFoldersAdapter adapter;
    ArrayList<FileInfo> alreadySelectedFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView gvFolders = (GridView) findViewById(R.id.gvFolders);
//        alreadySelectedFiles = (ArrayList<FileInfo>) getIntent().getSerializableExtra(CameraItemsFragment.CAMERA_ITEMS_SELECTED_FILES);
        adapter = new ImagesFoldersAdapter(this, folders);
        gvFolders.setAdapter(adapter);

        ArrayList<FileInfo> foldersAvailable = getFolders();
        folders.addAll(foldersAvailable);
        adapter.notifyDataSetChanged();
        gvFolders.setOnItemClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "On Activity Rresult");
        if(resultCode == FolderFiles.RESULT_SKIP_FOLDERS)
        {
            Log.e(TAG, "Skip Folders");
            Intent intent = new Intent();
//            ArrayList<FileInfo> fileInfos = (ArrayList<FileInfo>) data.getExtras().getSerializable(FolderFiles.SELECTED_FILES);
//            Log.e(TAG, "FileInfos Size " + fileInfos.size());
//            intent.putExtra(GALLERY_SELECTED_PHOTOS , data.getExtras().getSerializable(FolderFiles.SELECTED_FILES));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG ,"On Item Click " +  position + "");
        FileInfo fileInfo = folders.get(position);
        File file = new File(fileInfo.getFileName());
        Log.e(TAG ,"File path to open" + fileInfo.getFileName());
        ArrayList<FileInfo> filesInFolder = getFilesInFolder(file);
        Intent intent = new Intent(this,FolderFiles.class);
        intent.putExtra(FOLDER_NAME, fileInfo.getDisplayName());
        intent.putExtra(FILES_IN_FOLDER, filesInFolder);
//        intent.putExtra(ALREADY_SELECTED_FILES , alreadySelectedFiles);
        startActivityForResult(intent, REQUEST_FOLDER_FILES);
    }


    private ArrayList<FileInfo> getFolders() {
        File pathPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.e(TAG, "External Path :" + pathPictures.toString());
        ArrayList<FileInfo> files1 = getAllFoldersInfo(pathPictures);

        File pathDCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        ArrayList<FileInfo> files2 = getAllFoldersInfo(pathDCIM);
        files1.addAll(files2);

        File pathWhatsApp = new File(Environment.getExternalStorageDirectory()+"/WhatsApp/Media");
        Log.e(TAG , pathWhatsApp.getAbsolutePath());
        ArrayList<FileInfo> files3 = getAllFoldersInfo(pathWhatsApp);
        for(FileInfo fileInfo : files3)
        {
            if(fileInfo.getType().equals(FileInfo.FILE_TYPE.FOLDER))
            {
                if(fileInfo.getDisplayName().equals("WhatsApp Images"))
                    files1.add(fileInfo);
            }
        }

        return files1;
    }

    private ArrayList<FileInfo> getAllFoldersInfo(File file) {

        ArrayList<FileInfo> allFiles = new ArrayList<FileInfo>();
        File[] contentPictures = file.listFiles();

        if (contentPictures.length == 0) {
            Log.e(TAG, "No Files found at the path mentioned");
        }
        else
        {
            for (File folder : contentPictures) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setDisplayName(folder.getName());
                fileInfo.setFileName(folder.getAbsolutePath());
                File[] imagesInFolder = folder.listFiles();
                if(imagesInFolder!=null)
                {
                    if ( imagesInFolder.length == 0) {
                        Log.e("File Type ", "Image");
                        fileInfo.setFilePath(folder.getAbsolutePath());
                    } else {
                        Log.e("File Type ", "Folder");
                        fileInfo.setType(FileInfo.FILE_TYPE.FOLDER);
                        fileInfo.setFileCount(imagesInFolder.length);
                        fileInfo.setFilePath(imagesInFolder[imagesInFolder.length - 1].getAbsolutePath());
                        Log.e("Last File Path", imagesInFolder[imagesInFolder.length - 1].getAbsolutePath());
                    }
                    allFiles.add(fileInfo);
                }
            }
        }
        return allFiles;
    }

    private ArrayList<FileInfo> getFilesInFolder(File file) {
        ArrayList<FileInfo> allFiles = new ArrayList<FileInfo>();
        File[] contentPictures = file.listFiles();

        if (contentPictures.length == 0) {
            Log.e(TAG, "No Files found at the path mentioned");
        }
        else
        {
            for (File individualFile : contentPictures) {
                Log.e("File name ", individualFile.getName());
                FileInfo fileInfo = new FileInfo();
                fileInfo.setDisplayName(individualFile.getName());
                fileInfo.setFilePath(individualFile.getAbsolutePath());

                if(checkIfAlreadyPresent(fileInfo))
                    fileInfo.setSelected(true);
                allFiles.add(fileInfo);
            }
        }
        return allFiles;
    }

    public boolean checkIfAlreadyPresent(FileInfo fileInfo)
    {
        if(ApplicationController.selectedImages.size()>0)
        {
            for(FileInfo fileInfo1 : ApplicationController.selectedImages)
            {
                if(fileInfo1.getFilePath().equals(fileInfo.getFilePath()))
                    return true;
            }
        }
        return false;
    }
}
