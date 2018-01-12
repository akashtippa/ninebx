package com.ninebx.ui.home.lists.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ninebx.R;
import com.ninebx.ui.home.lists.model.AddedItem;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder> {
    private ArrayList<AddedItem> myList;
    int mLastPosition = 0;

    public RecyclerAdapter(ArrayList<AddedItem> myList) {
        this.myList = myList;
    }

    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        RecyclerItemViewHolder holder = new RecyclerItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerItemViewHolder holder, final int position) {
        holder.etTitleTextView.setText(myList.get(position).getStrAddedItem());
        mLastPosition = position;
    }

    @Override
    public int getItemCount() {
        return (null != myList ? myList.size() : 0);
    }

    public void notifyData(ArrayList<AddedItem> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private final EditText etTitleTextView;

        public RecyclerItemViewHolder(final View parent) {
            super(parent);
            etTitleTextView = (EditText) parent.findViewById(R.id.edtAddedList);
        }
    }
}