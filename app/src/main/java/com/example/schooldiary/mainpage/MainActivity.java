package com.example.schooldiary.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.schooldiary.R;
import com.example.schooldiary.mainpage.bottomNavigation.DiaryFragment;
import com.example.schooldiary.mainpage.bottomNavigation.MarksFragment;
import com.example.schooldiary.mainpage.bottomNavigation.NewsFragment;
import com.example.schooldiary.mainpage.bottomNavigation.more.FinalGradesFragment;
import com.example.schooldiary.mainpage.bottomNavigation.more.SettingsFragment;
import com.example.schooldiary.mainpage.bottomNavigation.more.TimetableOfRingsFragment;
import com.example.schooldiary.mainpage.bottomNavigation.more.TimetableOfVacationFragment;
import com.example.schooldiary.registration.RegistrationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements
        NewsFragment.OnFragmentInteractionListener,
        DiaryFragment.OnFragmentInteractionListener,
        MarksFragment.OnFragmentInteractionListener,
        TimetableOfRingsFragment.OnFragmentInteractionListener,
        TimetableOfVacationFragment.OnFragmentInteractionListener,
        FinalGradesFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    CheckLogInUser checkLogInUser;
    Toolbar toolbar;
    NavController navController;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLogInUser = CheckLogInUser.getInstance();
        if (!checkLogInUser.isLoggedInUser()) {
            Log.d("isLoggedInUser", String.valueOf(checkLogInUser.isLoggedInUser()));
            startRegistrationActivity();
        }

        // Find the toolbar_items view inside the activity layout
        toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar_items exists in the activity and is not null
        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_news, R.id.navigation_diary, R.id.navigation_marks)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    // Menu icons are inflated just as they were with actionbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar_items, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_timetable_of_rings:
                navController.navigate(R.id.navigation_timetable_of_rings);
                break;
            case R.id.action_timetable_of_vacation:
                navController.navigate(R.id.navigation_timetable_of_vacation);
                break;
            case R.id.action_final_grades:
                navController.navigate(R.id.navigation_final_grades);
                break;
            case R.id.action_settings:
                navController.navigate(R.id.navigation_settings);
                break;
            case R.id.action_sign_out:
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                startRegistrationActivity();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startRegistrationActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
