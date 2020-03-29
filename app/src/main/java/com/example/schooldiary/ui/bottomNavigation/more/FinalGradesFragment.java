package com.example.schooldiary.ui.bottomNavigation.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentFinalGradesBinding;
import com.example.schooldiary.model.Marks;
import com.example.schooldiary.ui.adapters.finalGrades.FinalGradesHolder;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FinalGradesFragment extends Fragment {

    private FirestoreRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_final_grades, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FragmentFinalGradesBinding binding = FragmentFinalGradesBinding.bind(view);
        RecyclerView gradesRecyclerView = binding.finalGradesRecyclerView;

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("finalGrades");

        FirestoreRecyclerOptions<Marks> options = new FirestoreRecyclerOptions.Builder<Marks>()
                .setQuery(query, Marks.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Marks, FinalGradesHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull FinalGradesHolder holder, int position, @NonNull Marks model) {
                holder.bindData(model);
            }

            @NonNull
            @Override
            public FinalGradesHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.item_marks for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_final_grade, group, false);

                return new FinalGradesHolder(view);
            }

            @Override
            public int getItemCount() {
                return super.getItemCount();
            }
        };

        gradesRecyclerView.setAdapter(adapter);
        gradesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
