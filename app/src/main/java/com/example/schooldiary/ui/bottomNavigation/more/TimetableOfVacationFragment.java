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
import com.example.schooldiary.databinding.FragmentTimetableOfVacationBinding;
import com.example.schooldiary.model.Dates;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;
import com.example.schooldiary.ui.adapters.holidays.HolidaysHolder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class TimetableOfVacationFragment extends Fragment {

    private FirestoreRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timetable_of_vacation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FragmentTimetableOfVacationBinding binding = FragmentTimetableOfVacationBinding.bind(view);
        RecyclerView HolidaysRecyclerView = binding.holidaysRecyclerView;

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("holidays")
                .orderBy("serialNumber");

        FirestoreRecyclerOptions<Dates> options = new FirestoreRecyclerOptions.Builder<Dates>()
                .setQuery(query, Dates.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Dates, HolidaysHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull HolidaysHolder holder, int position, @NonNull Dates model) {
                holder.bindData(model);
            }

            @NonNull
            @Override
            public HolidaysHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_holidays, group, false);

                return new HolidaysHolder(view);
            }

            @Override
            public int getItemCount() {
                return super.getItemCount();
            }
        };

        HolidaysRecyclerView.setAdapter(adapter);
        HolidaysRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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
