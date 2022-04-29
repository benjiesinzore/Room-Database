package com.benjaminsinzore.roomdatabase.roomdb;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


public class WordListAdapter extends ListAdapter<Word, WordViewHolder> {

    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(diffCallback);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        com.example.android.roomwordssample.Word current = getItem(position);
        holder.bind(current.getWord());
    }

    static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull com.example.android.roomwordssample.Word oldItem, @NonNull com.example.android.roomwordssample.Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull com.example.android.roomwordssample.Word oldItem, @NonNull com.example.android.roomwordssample.Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }
}
