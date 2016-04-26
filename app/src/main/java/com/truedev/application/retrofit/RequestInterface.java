package com.truedev.application.retrofit;


import com.truedev.application.models.AutoSuggestResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by Lakshay on 20/07/15.
 */
public interface RequestInterface {

    @GET("/core/{path}/")
    void getSuggestions(@Path("path") String path, @QueryMap Map<String, String> params, Callback<AutoSuggestResponse> callback);

}
