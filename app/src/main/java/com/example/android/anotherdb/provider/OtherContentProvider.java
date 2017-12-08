package com.example.android.anotherdb.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.anotherdb.provider.OtherContract.Table1Entry;

/**
 * Created by dmidma on 12/7/17.
 */

public class OtherContentProvider extends ContentProvider {


    // initialize DB Helper in onCreate
    private OtherDbHelper mOtherDbHelper;


    // Codes to match with URIs
    public static final int CODE_TABLE = 100;
    public static final int CODE_TABLE_WITH_INT = 101;
    public static final int CODE_TABLE_WITH_STRING = 102;

    // the uri matcher
    private static final UriMatcher sUriMatcher = buildUriMatcher();


    // method to associate URI's with their int
    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(OtherContract.AUTHORITY, OtherContract.PATH_TO_TABLE_1, CODE_TABLE);
        uriMatcher.addURI(OtherContract.AUTHORITY, OtherContract.PATH_TO_TABLE_1 + "/#", CODE_TABLE_WITH_INT);
        uriMatcher.addURI(OtherContract.AUTHORITY, OtherContract.PATH_TO_TABLE_1 + "/*", CODE_TABLE_WITH_STRING);

        return uriMatcher;
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        mOtherDbHelper = new OtherDbHelper(context);
        return true;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final SQLiteDatabase db = mOtherDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        Uri returnUri;

        switch (match) {
            case CODE_TABLE:
                long id = db.insert(Table1Entry.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(Table1Entry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into DB");
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // notify the resolver if the u
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {

        // read only
        final SQLiteDatabase db = mOtherDbHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {
            case CODE_TABLE:
                retCursor = db.query(Table1Entry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // set a notification URI on the cursor and return it
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        throw new RuntimeException("Not Implemented Yet");
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        throw new RuntimeException("Not Implemented Yet");
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
