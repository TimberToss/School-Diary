package com.example.schooldiary.ui.registration;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.schooldiary.R;

public class ChooseClassFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_class, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button nameEntryButton = view.findViewById(R.id.continue_btn);
        nameEntryButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.registrationFragment, null));
    }

}
