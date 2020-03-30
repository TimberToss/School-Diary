package com.example.schooldiary.ui.adapters.diary;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.databinding.ItemDayBinding;
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

    public synchronized void bindData(Day day) {

        ItemDayBinding binding = ItemDayBinding.bind(itemView);

        TextView dayName = binding.dayName;
        RecyclerView subjectsRecyclerView = binding.subjectsRecyclerView;

        dayName.setText(new StringBuilder().append(day.getName()).append(", 16 September").toString());
        Log.d("DayName", day.getName());

        Query query = FirebaseFirestore.getInstance()
                .collection("days")
                .document(day.getName())
                .collection("subjects")
                .orderBy("serialNumber");

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