package com.example.android.anotherdb.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dmidma on 12/7/17.
 */

public class OtherContract {

    // authority for the Content Provider
    public static final String AUTHORITY = "com.example.android.anotherdb";

    // base for all URIs
    public static Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    // define paths for tables to be able to: BASE_CONTENT_URI/PATH_TO_TABLE
    public static final String PATH_TO_TABLE_1 = "table_1";

    // define table and its content
    public static final class Table1Entry implements BaseColumns {

        // build the content uri
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TO_TABLE_1).build();


        // table name
        public static final String TABLE_NAME = "table_1";

        // columns name
        public static final String COLUMN_TEXT = "column_name_1";
        public static final String COLUMN_NUMBER = "column_name_2";
    }

}
