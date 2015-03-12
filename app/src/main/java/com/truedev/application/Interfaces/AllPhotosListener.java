package com.truedev.application.Interfaces;

import com.truedev.application.FileInfo;

import java.util.ArrayList;

/**
 * Created by Lakshay on 05-03-2015.
 */
public interface AllPhotosListener {
        public void selectFile(FileInfo fileInfo);
        public void unSelectFile(FileInfo fileInfo);
        public void updateFiles(ArrayList<FileInfo> fileInfos);
        public boolean checkIfSelected(FileInfo fileInfo);
}
