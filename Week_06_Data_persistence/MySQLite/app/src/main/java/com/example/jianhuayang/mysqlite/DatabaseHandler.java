package com.example.jianhuayang.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jianhuayang on 01/11/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    public DatabaseHandler(Context context){
        super(context, "testDB", null, 1);
    }


    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE contactTable (colID, colName, colPhone)");
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
