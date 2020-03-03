package com.example.schooldiary.ui.adapters.diary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Day;
import com.example.schooldiary.model.Subject;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;

import java.util.List;


public class DayHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public DayHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(Day day) {

        List<Subject> subjects = day.getSubjects();

        TextView dayName = getItemView().findViewById(R.id.day_name);
        RecyclerView subjectsRecyclerView = getItemView().findViewById(R.id.subjects_recycler_view);

        dayName.setText(day.getName());

//        FirestoreRecyclerAdapter adapter = new DiaryFragmentAdapter();
//
//        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        newsRecyclerView.setAdapter(adapter);

    }
}
