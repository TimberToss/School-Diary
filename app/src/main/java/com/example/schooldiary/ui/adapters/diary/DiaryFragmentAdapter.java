package com.example.schooldiary.ui.adapters.diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Day;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;


public class DiaryFragmentAdapter extends FirestoreRecyclerAdapter<Day, DayHolder> {

    public DiaryFragmentAdapter(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }

    @NonNull
    @Override
    public DayHolder onCreateViewHolder(ViewGroup group, int i) {

        View view = LayoutInflater.from(group.getContext())
                .inflate(R.layout.item_day, group, false);

        return new DayHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull DayHolder holder, int position, @NonNull Day model) {
        holder.bindData(model);
    }
}
