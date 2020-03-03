package com.example.schooldiary.ui.adapters.diary;

import android.view.View;
import android.widget.TextView;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Day;
import com.example.schooldiary.model.Subject;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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

        TextView dayName = getItemView().findViewById(R.id.day_name);
        RecyclerView subjectsRecyclerView = getItemView().findViewById(R.id.subjects_recycler_view);

        dayName.setText(day.getName());
        Log.d("DayName", day.getName());

        Query query = FirebaseFirestore.getInstance()
                .collection("days")
                .document(day.getName())
                .collection("subjects")
                .orderBy("serialNumber");

        query.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                Log.d("QueryError",e.getMessage());
                return;
            }

            List<Subject> subjects = snapshot.toObjects(Subject.class);

            SubjectAdapter adapter = new SubjectAdapter(subjects);
            subjectsRecyclerView.setAdapter(adapter);
            subjectsRecyclerView.setLayoutManager(new LinearLayoutManager(getItemView().getContext()));
            subjectsRecyclerView.setNestedScrollingEnabled(false); //disable scrolling
        });
    }
}
