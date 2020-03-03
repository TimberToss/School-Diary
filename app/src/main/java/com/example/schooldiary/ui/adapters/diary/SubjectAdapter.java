package com.example.schooldiary.ui.adapters.diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Subject;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;


public class SubjectAdapter extends FirestoreRecyclerAdapter<Subject, SubjectHolder> {

    public SubjectAdapter(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(ViewGroup group, int i) {
        // Create a new instance of the ViewHolder, in this case we are using a custom
        // layout called R.layout.item_news for each item
        View view = LayoutInflater.from(group.getContext())
                .inflate(R.layout.item_subject, group, false);

        return new SubjectHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull SubjectHolder holder, int position, @NonNull Subject model) {
        holder.bindData(model);
    }
}