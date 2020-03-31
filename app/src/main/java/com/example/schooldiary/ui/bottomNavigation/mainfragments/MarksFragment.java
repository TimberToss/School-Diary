package com.example.schooldiary.ui.bottomNavigation.mainfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentMarksBinding;
import com.example.schooldiary.model.Marks;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;
import com.example.schooldiary.ui.adapters.marks.MarksHolder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class MarksFragment extends Fragment {

    private FirestoreRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_marks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FragmentMarksBinding binding = FragmentMarksBinding.bind(view);
        RecyclerView marksRecyclerView = binding.marksRecyclerView;

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("subjects")
                .orderBy("name", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Marks> options = new FirestoreRecyclerOptions.Builder<Marks>()
                .setQuery(query, Marks.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Marks, MarksHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull MarksHolder holder, int position, @NonNull Marks model) {
                holder.bindData(model);
            }

            @NonNull
            @Override
            public MarksHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_mark, group, false);

                return new MarksHolder(view);
            }

            @Override
            public int getItemCount() {
                return super.getItemCount();
            }
        };

        marksRecyclerView.setAdapter(adapter);
        marksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
