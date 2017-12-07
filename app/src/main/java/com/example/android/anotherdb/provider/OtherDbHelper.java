package com.example.android.anotherdb.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.anotherdb.provider.OtherContract.Table1Entry;
/**
 * Created by dmidma on 12/7/17.
 */

public class OtherDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "other.db";
    // increment the version if you change the DB
    private static final int DATABASE_VERSION = 1;



    public OtherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLENAME_TABLE = "CREATE TABLE " + Table1Entry.TABLE_NAME + " (" +
                Table1Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Table1Entry.COLUMN_TEXT + " TEXT NOT NULL, " +
                Table1Entry.COLUMN_NUMBER + " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLENAME_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table1Entry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
