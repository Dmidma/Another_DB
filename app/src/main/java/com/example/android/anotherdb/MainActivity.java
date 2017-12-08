package com.example.android.anotherdb;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContentResolverCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.anotherdb.provider.OtherContract;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    private Context mContext;

    private EditText mEtString;
    private EditText mEtInt;
    private EditText mEtSearch;

    private Button mBtnAdd;

    private ImageButton mIbtnSearch;

    private ListView mLvRows;

    private RowAdapter mAdapter;

    private ArrayList<String[]> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        mEtInt = (EditText) findViewById(R.id.et_int);
        mEtSearch = (EditText) findViewById(R.id.et_search_string);
        mEtString = (EditText) findViewById(R.id.et_string);

        mBtnAdd = (Button) findViewById(R.id.btn_add_row);
        mBtnAdd.setOnClickListener(this);

        mIbtnSearch = (ImageButton) findViewById(R.id.ibtn_search_string);
        mIbtnSearch.setOnClickListener(this);

        mLvRows = (ListView) findViewById(R.id.lv_rows);
        mData = new ArrayList<String[]>();
        mData.add(new String[] {"1", "No Data", "Yet"});
        mAdapter = new RowAdapter(mContext, mData);
        mLvRows.setAdapter(mAdapter);


        // fetch Data
        new FetchRows().execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_row:
                addRow();
                break;
            case R.id.ibtn_search_string:
                break;
        }
    }

    private void addRow() {

        ContentValues values = new ContentValues();

        String intString = mEtInt.getText().toString();
        String stringString = mEtString.getText().toString();

        mData.add(new String[] {"5", stringString, intString.toString()});
        mAdapter.setData(mData);

        mEtInt.setText("");
        mEtString.setText("");

        values.put(OtherContract.Table1Entry.COLUMN_NUMBER, Integer.parseInt(intString));
        values.put(OtherContract.Table1Entry.COLUMN_TEXT, stringString);

        Uri uri = getContentResolver().insert(OtherContract.Table1Entry.CONTENT_URI, values);
        if (uri != null)
            Toast.makeText(mContext, uri.toString(), Toast.LENGTH_LONG).show();
    }

    public class FetchRows extends AsyncTask<Void, Void, Cursor> {


        @Override
        protected Cursor doInBackground(Void... voids) {

            ContentResolver resolver = getContentResolver();

            Cursor cursor = resolver.query(OtherContract.Table1Entry.CONTENT_URI, null, null, null, null);

            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);


            mData = new ArrayList<String[]>();


            if (cursor != null) {

                // point to the first
                cursor.moveToFirst();

                do {
                    int i = cursor.getInt(cursor.getColumnIndex(OtherContract.Table1Entry.COLUMN_NUMBER));
                    int id = cursor.getInt(cursor.getColumnIndex(OtherContract.Table1Entry._ID));
                    String text = cursor.getString(cursor.getColumnIndex(OtherContract.Table1Entry.COLUMN_TEXT));

                    mData.add(new String [] {id + "", text, i + ""});
                } while (cursor.moveToNext());


            }

            mAdapter.setData(mData);
        }
    }


}
