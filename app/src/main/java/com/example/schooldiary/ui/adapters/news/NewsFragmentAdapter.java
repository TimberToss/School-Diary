package com.example.schooldiary.ui.adapters.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.schooldiary.R;
import com.example.schooldiary.model.News;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;

public class NewsFragmentAdapter extends FirestoreRecyclerAdapter<News, NewsHolder> {

    private NewsHolder.NewsClickListener listener;

    public NewsFragmentAdapter(@NonNull FirestoreRecyclerOptions options, NewsHolder.NewsClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext())
                .inflate(R.layout.item_news, group, false);

        return new NewsHolder(view, listener);
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsHolder holder, int position, @NonNull News model) {
        holder.bindData(model);
    }
}
