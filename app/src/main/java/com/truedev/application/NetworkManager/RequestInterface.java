package com.truedev.application.NetworkManager;

import com.truedev.application.models.GeneralResponse;

import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public interface RequestInterface {

    @Multipart
    @POST("/webapis/evaluation/{path}")
    GeneralResponse uploadImage(@Path("path") String methodImageUpload, @Part(NetworkManager.CERTIFICATION_IMAGE) TypedFile file, @Part(NetworkManager.EVALUATION_DATA) String params);
}
