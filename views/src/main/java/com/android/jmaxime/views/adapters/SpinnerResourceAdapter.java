package com.android.jmaxime.views.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.android.jmaxime.views.R;
import com.android.jmaxime.views.interfaces.SpinnerDialogItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpinnerResourceAdapter<T extends SpinnerDialogItem> extends RecyclerView.Adapter<SpinnerResourceAdapter.ResourceHolder<T>> {

    private List<T> mList;
    private List<T> mListFiltered;
    private String mDefaultSelectedItem;
    private String mEmpty;

    public SpinnerResourceAdapter(List<T> list) {
        this("", "", list, false);
    }

    public SpinnerResourceAdapter(T defaultSelectedItem, List<T> list) {
        this((defaultSelectedItem != null) ? defaultSelectedItem.getId() : null, "", list, false);
    }

    public SpinnerResourceAdapter(T defaultSelectedItem, String empty, List<T> list) {
        this((defaultSelectedItem != null) ? defaultSelectedItem.getId() : null, empty, list, false);
    }

    public SpinnerResourceAdapter(T defaultSelectedItem, String empty, List<T> list, boolean addEmptyChoice) {
        this((defaultSelectedItem != null) ? defaultSelectedItem.getId() : null, empty, list, addEmptyChoice);
    }

    private SpinnerResourceAdapter(String defaultSelectedItem, String empty, List<T> list, boolean addEmptyChoice) {
        mDefaultSelectedItem = defaultSelectedItem;
        mList = list;
        if (addEmptyChoice) {
            mList.add(0, null);
        }
        mListFiltered = list;
        mEmpty = empty;
    }

    @Override
    public ResourceHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_selected_value_recycler, parent, false);
        return new ResourceHolder<>(v, mEmpty);
    }

    @Override
    public void onBindViewHolder(ResourceHolder<T> holder, int position) {
        holder.onBind(mListFiltered.get(position), isSelectedItem(mListFiltered.get(position)));
    }

    @Override
    public int getItemCount() {
        return mListFiltered.size();
    }

    private boolean isSelectedItem(T item) {
        return Objects.equals(mDefaultSelectedItem, item != null ? item.getId() : null);
    }

    public int getRealPosition(T item) {
        return mList.indexOf(item);
    }

    public T getItem(int position) {
        return mListFiltered.get(position);
    }

    public void selectedItem(int position) {
        T item = getItem(position);
        mDefaultSelectedItem = item != null ? item.getId() : "";
        notifyDataSetChanged();
    }

    public void setChoice(ArrayList<T> itemChoices) {
        mList = itemChoices;
        mListFiltered = itemChoices;
        notifyDataSetChanged();
    }

    public void setFilterChoice(String value) {
        mListFiltered = new ArrayList<>();
        for (T t : mList) {
            if (t.getTitle().toUpperCase().contains(value.toUpperCase())) {
                mListFiltered.add(t);
            }
        }
        notifyDataSetChanged();
    }

    static class ResourceHolder<T extends SpinnerDialogItem> extends RecyclerView.ViewHolder {

        private final CheckedTextView mCheckedTextView;
        private final String mEmpty;
        private Drawable mDrawable;

        ResourceHolder(View itemView, String empty) {
            super(itemView);
            mEmpty = empty;
            mCheckedTextView = itemView.findViewById(R.id.check_text_choice);
        }

        void onBind(T item, boolean isSelected) {
            if (item == null) {
                mCheckedTextView.setText(mEmpty);
                mDrawable = mCheckedTextView.getCheckMarkDrawable();
                mCheckedTextView.setCheckMarkDrawable(null);
            } else {
                mCheckedTextView.setText(item.getTitle());
                mCheckedTextView.setChecked(isSelected);
                if (mCheckedTextView.getCheckMarkDrawable() == null) {
                    mCheckedTextView.setCheckMarkDrawable(mDrawable);
                }
            }
        }
    }
}
