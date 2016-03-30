package com.truedev.application.NetworkManager;

import com.truedev.application.models.GeneralResponse;
import com.truedev.application.retrofit.RetrofitAdapter;

import java.io.File;

import retrofit.mime.TypedFile;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class NetworkManager {

    public static final int CONNECTION_POOL_SIZE = 4;
    public static final String CERTIFICATION_IMAGE = "certImg";
    public static final String EVALUATION_DATA = "evaluationData";
    private static final String METHOD_IMAGE_UPLOAD = "addCertificationImage";

    public static RequestInterface requestInterface = RetrofitAdapter.getRestAdapter().create(RequestInterface.class);

    public static String SUCCESS = "T";

    public static GeneralResponse makeImageUploadRequest(String imagePath, String params) {
        TypedFile file = new TypedFile("image/*", new File(imagePath));
        return requestInterface.uploadImage(NetworkManager.METHOD_IMAGE_UPLOAD, file, params);
    }
}
