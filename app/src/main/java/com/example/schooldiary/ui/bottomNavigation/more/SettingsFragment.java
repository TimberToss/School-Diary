package com.example.schooldiary.ui.bottomNavigation.more;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.schooldiary.R;
import com.example.schooldiary.ui.registration.RegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        Activity activity = getActivity();
        Button signOutButton = activity.findViewById(R.id.sign_out_btn);

        signOutButton.setOnClickListener((view1) -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Intent intent = new Intent(activity, RegistrationActivity.class);
            startActivity(intent);
            activity.finish();
        });
    }

}
