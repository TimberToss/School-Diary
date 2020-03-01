package com.example.schooldiary.mainpage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.schooldiary.R;
import com.example.schooldiary.mainpage.bottomNavigation.DiaryFragment;
import com.example.schooldiary.mainpage.bottomNavigation.MarksFragment;
import com.example.schooldiary.mainpage.bottomNavigation.MoreFragment;
import com.example.schooldiary.mainpage.bottomNavigation.NewsFragment;
import com.example.schooldiary.mainpage.bottomNavigation.more.FinalGradesFragment;
import com.example.schooldiary.mainpage.bottomNavigation.more.SettingsFragment;
import com.example.schooldiary.mainpage.bottomNavigation.more.TimetableOfRingsFragment;
import com.example.schooldiary.mainpage.bottomNavigation.more.TimetableOfVacationFragment;
import com.example.schooldiary.mainpage.model.News;
import com.example.schooldiary.mainpage.ui.FirestoreRecyclerAdapter;
import com.example.schooldiary.mainpage.ui.FirestoreRecyclerOptions;
import com.example.schooldiary.mainpage.ui.NewsHolder;
import com.example.schooldiary.registration.RegistrationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        NewsFragment.OnFragmentInteractionListener,
        DiaryFragment.OnFragmentInteractionListener,
        MarksFragment.OnFragmentInteractionListener,
        MoreFragment.OnFragmentInteractionListener,
        TimetableOfRingsFragment.OnFragmentInteractionListener,
        TimetableOfVacationFragment.OnFragmentInteractionListener,
        FinalGradesFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener {

    private CheckLogInUser checkLogInUser;
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    private FirebaseFirestore firestore;
    private Query query;
    private RecyclerView newsRecyclerView;
    private FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLogInUser = CheckLogInUser.getInstance();
        if (!checkLogInUser.isLoggedInUser()) {
            Log.d("isLoggedInUser", String.valueOf(checkLogInUser.isLoggedInUser()));
            startRegistrationActivity();
        }


        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_news, R.id.navigation_diary, R.id.navigation_marks, R.id.navigation_more)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private void startRegistrationActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}
}
