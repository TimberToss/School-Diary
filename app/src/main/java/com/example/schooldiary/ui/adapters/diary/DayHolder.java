package com.example.schooldiary.ui.adapters.diary;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Day;
import com.example.schooldiary.model.Subject;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

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

        int i = 1;

        query.get(Source.CACHE) // this data don't disappear by clear cache, but by clear data of app
                .addOnCompleteListener(task -> {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (!querySnapshot.isEmpty()) {
                        inflateAdapter(querySnapshot, subjectsRecyclerView);
                    } else {
                        query.get(Source.SERVER)
                                .addOnCompleteListener(newTask -> {
                                    QuerySnapshot documentSnapshot = newTask.getResult();
                                    inflateAdapter(documentSnapshot, subjectsRecyclerView);
                                });
                    }
                });
    }

    private void inflateAdapter(QuerySnapshot querySnapshot, RecyclerView subjectsRecyclerView) {
        List<Subject> subjects = querySnapshot.toObjects(Subject.class);
        Log.d("Query on subjects", subjects.size() + "");


        SubjectAdapter adapter = new SubjectAdapter(subjects);
        subjectsRecyclerView.setAdapter(adapter);
        subjectsRecyclerView.setLayoutManager(new LinearLayoutManager(subjectsRecyclerView.getContext()));
        subjectsRecyclerView.setNestedScrollingEnabled(false); //disable scrolling
    }
}