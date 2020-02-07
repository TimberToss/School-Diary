package com.example.schooldiary.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.schooldiary.R;
import com.example.schooldiary.registration.RegistrationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity implements
        NewsFragment.OnFragmentInteractionListener,
        DiaryFragment.OnFragmentInteractionListener,
        MarksFragment.OnFragmentInteractionListener,
        TimetableOfLessonsFragment.OnFragmentInteractionListener,
        TimetableOfVacationFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    CheckLogInUser checkLogInUser;


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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.action_news:
                    navController.navigate(R.id.newsFragment);
                    return true;
                case R.id.action_diary:
                    navController.navigate(R.id.diaryFragment);
                    return true;
                case R.id.action_marks:
                    navController.navigate(R.id.marksFragment);
                    return true;
                case R.id.action_timetable:
                    navController.navigate(R.id.timetableOfLessonsFragment);
                    return true;
                case R.id.action_settings:
                    navController.navigate(R.id.settingsFragment);
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
