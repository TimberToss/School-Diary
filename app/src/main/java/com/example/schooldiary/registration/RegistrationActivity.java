package com.example.schooldiary.registration;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schooldiary.R;

public class RegistrationActivity extends AppCompatActivity implements
        ChooseClassFragment.OnFragmentInteractionListener,
        RegistrationFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
