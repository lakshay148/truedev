package com.truedev.application.Utils;

/**
 * Created by Lakshay on 12-03-2015.
 */
public class PhotoUploadParams {


    public enum FolderOptions{
        PUBLIC_DIR , PUBLIC_DIR_DCIM , PUBLIC_DIR_SOCIAL , PUBLIC_DIR_ALL;
    }

    public enum CameraOrientation{
        LANDSCAPE , PORTRAIT , BOTH;
    }

    public PhotoUploadParams(String uploadApi) {
        this.uploadApi = uploadApi;
    }

    private CameraOrientation orientation;
    private int noOfPhotos;
    private FolderOptions folderOptions;
    private Boolean tagEnabled;
    private Boolean metaEnabled;
    private String uploadApi;

    public int getNoOfPhotos() {
        return noOfPhotos;
    }

    public void setNoOfPhotos(int noOfPhotos) {
        this.noOfPhotos = noOfPhotos;
    }

    public CameraOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(CameraOrientation orientation) {
        this.orientation = orientation;
    }

    public FolderOptions getFolderOptions() {
        return folderOptions;
    }

    public void setFolderOptions(FolderOptions folderOptions) {
        this.folderOptions = folderOptions;
    }

    public Boolean getTagEnabled() {
        return tagEnabled;
    }

    public void setTagEnabled(Boolean tagEnabled) {
        this.tagEnabled = tagEnabled;
    }

    public Boolean getMetaEnabled() {
        return metaEnabled;
    }

    public void setMetaEnabled(Boolean metaEnabled) {
        this.metaEnabled = metaEnabled;
    }

    public String getUploadApi() {
        return uploadApi;
    }

    public void setUploadApi(String uploadApi) {
        this.uploadApi = uploadApi;
    }
}
