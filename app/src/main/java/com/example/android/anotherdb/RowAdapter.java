package com.example.android.anotherdb;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dmidma on 12/8/17.
 */

public class RowAdapter extends BaseAdapter {

    private Context mContext;

    private List<String[]> mData;

    public RowAdapter(Context context, List<String[]> data) {
        mContext = context;
        mData = data;
    }

    public void setData(List<String[]> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ConstraintLayout constraintLayout = (ConstraintLayout) layoutInflater.inflate(R.layout.row_item, null);

        TextView rowInt = (TextView) constraintLayout.findViewById(R.id.tv_row_int);
        TextView rowString = (TextView) constraintLayout.findViewById(R.id.tv_row_string);

        rowString.setText(mData.get(i)[1]);
        rowInt.setText(mData.get(i)[2]);

        return constraintLayout;
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(mData.get(i)[0]);
    }
}
