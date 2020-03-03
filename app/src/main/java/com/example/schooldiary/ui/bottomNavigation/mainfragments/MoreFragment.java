package com.example.schooldiary.ui.bottomNavigation.mainfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.ItemFragment;
import com.example.schooldiary.ui.adapters.morefragments.FragmentAdapter;
import com.example.schooldiary.ui.adapters.morefragments.FragmentViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MoreFragment extends Fragment implements FragmentViewHolder.StudentsClickListener {

    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);
        recyclerView = root.findViewById(R.id.more_fragments_recycler_view);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        FragmentAdapter fragmentAdapter = new FragmentAdapter(this, createItemFragmentsList());
        recyclerView.setAdapter(fragmentAdapter);
    }

    @Override
    public void openFragment(int id) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(id);
    }

    private List<ItemFragment> createItemFragmentsList() {

        List<ItemFragment> list = new ArrayList<>();

        list.add(new ItemFragment(R.id.navigation_timetable_of_rings,
                getActivity().getResources().getDrawable(R.drawable.timetable_of_rings_24dp),
                "Ring's timetable"));

        list.add(new ItemFragment(R.id.navigation_timetable_of_vacation,
                getActivity().getResources().getDrawable(R.drawable.timetable_of_vacation_24dp),
                "Vacation's timetable"));

        list.add(new ItemFragment(R.id.navigation_final_grades,
                getActivity().getResources().getDrawable(R.drawable.final_grades_24dp),
                "Final grades"));

        list.add(new ItemFragment(R.id.navigation_settings,
                getActivity().getResources().getDrawable(R.drawable.settings_24dp),
                "Settings"));

        return list;
    }
}
