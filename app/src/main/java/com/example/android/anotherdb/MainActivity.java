package com.example.android.anotherdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
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
        View.OnClickListener,
        LoaderManager.LoaderCallbacks<Cursor> {

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
        mData.add(new String[] {"1", "Dmidma", "69"});
        mAdapter = new RowAdapter(mContext, mData);
        mLvRows.setAdapter(mAdapter);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
