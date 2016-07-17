package com.naneng.jiche.background.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nice on 16/3/21.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final int DBVERSION_ONE = 1;
    private static final String BDNAME = "nnjc";


    public DBHelper(Context context) {
        super(context, BDNAME, null, DBVERSION_ONE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        TableCart.create(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
