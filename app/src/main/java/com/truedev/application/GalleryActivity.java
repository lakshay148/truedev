package com.truedev.application;

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

import java.io.File;
import java.util.ArrayList;


public class GalleryActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "GalleryActivity";
    public static final String FILES_IN_FOLDER = "folder_files";
    public static final String FOLDER_NAME = "folder_name";
    private static final int REQUEST_FOLDER_FILES = 100;
    public static final String GALLERY_SELECTED_PHOTOS = "galleryPhotos";
    private ArrayList<FileInfo> folders = new ArrayList<FileInfo>();
    ImagesFoldersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView gvFolders = (GridView) findViewById(R.id.gvFolders);
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
        Log.e(TAG , "On Activity Rresult");
        if(resultCode == FolderFiles.RESULT_SKIP_FOLDERS)
        {
            Intent intent = new Intent();
            intent.putExtra(GALLERY_SELECTED_PHOTOS , data.getExtras().getSerializable(FolderFiles.SELECTED_FILES));
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
        Log.e("Position Clicked", position + "");

        FileInfo fileInfo = folders.get(position);
        File file = new File(fileInfo.getFileName());
        Log.e("File path to open", fileInfo.getFileName());
        ArrayList<FileInfo> filesInFolder = getFilesInFolder(file);
        Intent intent = new Intent(this,FolderFiles.class);
        intent.putExtra(FOLDER_NAME, fileInfo.getDisplayName());
        intent.putExtra(FILES_IN_FOLDER, filesInFolder);
        startActivityForResult(intent, REQUEST_FOLDER_FILES);
    }


    private ArrayList<FileInfo> getFolders() {
        File pathPictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.e(TAG, "External Path :" + pathPictures.toString());
        ArrayList<FileInfo> files = getAllFoldersInfo(pathPictures);
        return files;
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
                if (imagesInFolder.length == 0) {
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
                allFiles.add(fileInfo);
            }
        }
        return allFiles;
    }
}
