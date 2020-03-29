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
import com.example.schooldiary.databinding.FragmentTimetableOfRingsBinding;
import com.example.schooldiary.model.Dates;
import com.example.schooldiary.model.Marks;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;
import com.example.schooldiary.ui.adapters.marks.MarksHolder;
import com.example.schooldiary.ui.adapters.rings.RingsHolder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TimetableOfRingsFragment extends Fragment {


    private FirestoreRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timetable_of_rings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FragmentTimetableOfRingsBinding binding = FragmentTimetableOfRingsBinding.bind(view);
        RecyclerView ringsRecyclerView = binding.ringsRecyclerView;

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("rings");

        FirestoreRecyclerOptions<Dates> options = new FirestoreRecyclerOptions.Builder<Dates>()
                .setQuery(query, Dates.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Dates, RingsHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull RingsHolder holder, int position, @NonNull Dates model) {
                holder.bindData(model);
            }

            @NonNull
            @Override
            public RingsHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.item_marks for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_ring, group, false);

                return new RingsHolder(view);
            }

            @Override
            public int getItemCount() {
                return super.getItemCount();
            }
        };

        ringsRecyclerView.setAdapter(adapter);
        ringsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
