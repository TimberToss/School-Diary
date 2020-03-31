package com.example.schooldiary.ui.bottomNavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentShowSubjectBinding;


public class ShowSubjectFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_subject, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        FragmentShowSubjectBinding binding = FragmentShowSubjectBinding.bind(view);
        TextView subjectName= binding.subjectName;
        TextView subjectHomework = binding.subjectHomework;
        TextView subjectTeacher = binding.subjectTeacher;
        TextView subjectClassroom = binding.subjectClassroom;

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            String name = bundle.getString("Name");
            subjectName.setText(name);

            String homework = bundle.getString("Homework");
            subjectHomework.setText(homework);

            String teacher = bundle.getString("Teacher");
            subjectTeacher.setText(teacher);

            String classroom = bundle.getString("Classroom");
            subjectClassroom.setText(classroom);

        }
    }
}
