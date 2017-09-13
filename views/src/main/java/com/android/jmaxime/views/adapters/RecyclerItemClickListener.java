package com.android.jmaxime.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnChildAttachStateChangeListener {

    private final RecyclerView mRecycler;
    private final OnRecyclerOnItemClickListener mListener;

    /**
     * Constructor
     *
     * @param recyclerView
     * @param listener
     */
    public RecyclerItemClickListener(RecyclerView recyclerView, OnRecyclerOnItemClickListener listener) {
        mRecycler = recyclerView;
        mListener = listener;
    }

    /**
     * Add true on Item click on attached view
     *
     * @param recyclerView
     * @param listener
     */
    public static void affectOnItemClick(RecyclerView recyclerView, OnRecyclerOnItemClickListener listener) {
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerItemClickListener(recyclerView, listener));
    }


    @Override
    public void onChildViewAttachedToWindow(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnChildAttachedToWindow(v);
            }
        });
    }

    private void setOnChildAttachedToWindow(View v) {
        if (v != null && mListener != null) {
            int position = mRecycler.getChildLayoutPosition(v);
            if (position >= 0) {
                mListener.onItemClick(position, v);
            }
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {
        if (view == null) {
            return;
        }

        view.setOnClickListener(null);
    }

    /**
     * Interface for Recycler Item Click Listener
     */
    public interface OnRecyclerOnItemClickListener {
        /**
         * OnItemClick
         *
         * @param position
         * @param view
         */
        void onItemClick(int position, View view);


    }
}