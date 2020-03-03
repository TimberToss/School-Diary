package com.example.schooldiary.ui.registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.schooldiary.R;

public class RegistrationFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        Button signInButton = view.findViewById(R.id.sign_in_btn);
        signInButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.logInFragment));

        Button signUpButton = view.findViewById(R.id.sign_up_btn);
        signUpButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.createUserFragment));
    }
}
