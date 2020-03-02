package com.example.schooldiary.mainpage.bottomNavigation;

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
import com.example.schooldiary.mainpage.recyclerView.FragmentAdapter;
import com.example.schooldiary.mainpage.recyclerView.FragmentViewHolder;
import com.example.schooldiary.mainpage.recyclerView.ItemFragment;

import java.util.ArrayList;
import java.util.List;


public class MoreFragment extends Fragment implements FragmentViewHolder.StudentsClickListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FragmentAdapter fragmentAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        recyclerView = getActivity().findViewById(R.id.more_fragments_recycler_view);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        fragmentAdapter = new FragmentAdapter(this, createItemFragmentsList());
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
