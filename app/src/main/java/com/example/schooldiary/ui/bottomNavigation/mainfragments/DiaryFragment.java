package com.example.schooldiary.ui.bottomNavigation.mainfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentDiaryBinding;
import com.example.schooldiary.model.Day;
import com.example.schooldiary.ui.adapters.diary.DiaryFragmentAdapter;
import com.example.schooldiary.ui.adapters.diary.SubjectHolder;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DiaryFragment extends Fragment implements SubjectHolder.SubjectClickListener {

    private FirestoreRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FragmentDiaryBinding binding = FragmentDiaryBinding.bind(view);
        RecyclerView diaryRecyclerView = binding.diaryRecyclerView;

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("days")
                .orderBy("serialNumber", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Day> options = new FirestoreRecyclerOptions.Builder<Day>()
                .setQuery(query, Day.class)
                .build();

        adapter = new DiaryFragmentAdapter(options, this);

        diaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        diaryRecyclerView.setAdapter(adapter);
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

    @Override
    public void openFragment(int id, String name, String homework, String classroom, String teacher) {
        Bundle bundle = new Bundle();
        bundle.putString("Name", name);
        bundle.putString("Homework", homework);
        bundle.putString("Classroom", classroom);
        bundle.putString("Teacher", teacher);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(id, bundle);
    }
}
