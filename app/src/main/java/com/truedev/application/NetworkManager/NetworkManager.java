package com.truedev.application.NetworkManager;

import android.util.Log;

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
    private static final String METHOD_IMAGE_UPLOAD = "saveCertificationImage";

    public static RequestInterface requestInterface = RetrofitAdapter.getRestAdapter().create(RequestInterface.class);

    public static String SUCCESS = "T";
    private static final String TAG = "NetworkManager";

    public static GeneralResponse makeImageUploadRequest(String imagePath, String params) {
        TypedFile file = new TypedFile("image/*", new File(imagePath));
        Log.d(TAG, "makeImageUploadRequest: imagePath " + imagePath);
        Log.d(TAG, "makeImageUploadRequest: params "+ params);
//        params = "{\"apikey\":\"U3KqyrewdMuCotTS\",\"output\":\"json\",\"userKey\":\"rKKqoMbX59Hk0texo7na0sTb0aLO1OY=\",\"vcc_id\":503040,\"vc_id\":420491,\"tag_id\":\"10\",\"img_type\":\"2\",\"file_name\":\"abc_z.jpg\",\"source\":\"app\"}";
        return requestInterface.uploadImage(NetworkManager.METHOD_IMAGE_UPLOAD, file, params);
    }
}
