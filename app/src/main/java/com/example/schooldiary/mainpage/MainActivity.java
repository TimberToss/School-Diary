package com.example.schooldiary.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.schooldiary.R;
import com.example.schooldiary.mainpage.ui.FirestoreRecyclerAdapter;
import com.example.schooldiary.registration.RegistrationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

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
}
