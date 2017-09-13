package com.android.jmaxime.views.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.android.jmaxime.views.interfaces.SpinnerDialogItem;

import java.util.ArrayList;
import java.util.List;

public class SpinnerElementAdapter<T extends SpinnerDialogItem> extends ArrayAdapter<String> {

    private ArrayList<T> mList;

    public SpinnerElementAdapter(Context context, List<T> items) {
        this(context, new ArrayList<>(items));
    }

    public SpinnerElementAdapter(Context context, ArrayList<T> items) {
        super(context, android.R.layout.simple_spinner_item);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mList = items;
    }

    public ArrayList<T> getList() {
        return mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position).getTitle();
    }

    @Override
    public void clear() {
        super.clear();
        mList = new ArrayList<T>();
    }
}