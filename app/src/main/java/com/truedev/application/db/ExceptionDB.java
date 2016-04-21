package com.truedev.application.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExceptionDB extends SQLiteOpenHelper {
    private static final String TAG = "EvaluatorExceptionDb";

    private static final int DB_VERSION = 1;
    private static final String EXCEPTION_DB = "evalexception.db";
    public static final String UPDATE_TIME = "updatetime";
    public static final String ID = "_id";
    public static final String EXCEPTION_TABLE = "exceptions";
    public static final String EXCEPTION_MESSAGE = "message";
    public static final String TITLE = "title";
    public static final String USER_ID = "userID";
    public static final String EXCEPTION_TYPE = "exceptionType";
    private static final String EXCEPTION_SQL = "CREATE TABLE "
            + EXCEPTION_TABLE + " ("
            + ID + " INTEGER   PRIMARY KEY, "
            + EXCEPTION_MESSAGE + " TEXT NOT NULL, "
            + USER_ID + " TEXT, "
            + EXCEPTION_TYPE + " TEXT, "
            + UPDATE_TIME + " TEXT NOT NULL, UNIQUE ( " + ID + "))";

    public ExceptionDB(Context context) {
        super(context, EXCEPTION_DB, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EXCEPTION_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXCEPTION_TABLE);
        onCreate(db);
    }
}
