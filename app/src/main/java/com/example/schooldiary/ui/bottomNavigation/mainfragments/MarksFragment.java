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
import com.example.schooldiary.model.Marks;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;
import com.example.schooldiary.ui.adapters.marks.MarksHolder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;



public class MarksFragment extends Fragment {

    private RecyclerView marksRecyclerView;
    private FirestoreRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_marks, container, false);
        marksRecyclerView = root.findViewById(R.id.marks_recycler_view);
        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("subjects")
                .orderBy("name", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Marks> options = new FirestoreRecyclerOptions.Builder<Marks>()
                .setQuery(query, Marks.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Marks, MarksHolder>(options) {
            @Override
            public void onBindViewHolder(MarksHolder holder, int position, Marks model) {
                holder.bindData(model);
            }

            @NonNull
            @Override
            public MarksHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.item_marks for each item
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
