package com.truedev.application.syncadapter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by lakshaygirdhar on 30/9/15.
 */
public class AppContentProvider extends ContentProvider {


    static final String PROVIDER_NAME = "com.gaadi.evaluator.provider";
    static final String URL = "content://" + PROVIDER_NAME + "/evaluator";
    static final Uri CONTENT_URI = Uri.parse(URL);

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
