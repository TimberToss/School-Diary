package com.example.schooldiary.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.schooldiary.R;
import com.example.schooldiary.registration.RegistrationActivity;

public class MainActivity extends AppCompatActivity implements
        MainPageFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        boolean isStart = sharedPref.getBoolean("isStart", false);
        Log.d("TAG", isStart + "");
        if (!isStart) {
            Log.d("TAG", "OK");
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
