package com.example.schooldiary.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.schooldiary.R;
import com.example.schooldiary.registration.RegistrationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements
        MainPageFragment.OnFragmentInteractionListener {

    CheckLogInUser checkLogInUser;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLogInUser = CheckLogInUser.getInstance();
        if (!checkLogInUser.isLoggedInUser()) {
            Log.d("isLoggedInUser", String.valueOf(checkLogInUser.isLoggedInUser()));
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
            finish();
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.action_diary:
                    return true;
                case R.id.action_marks:
                    return true;
                case R.id.action_more:
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
