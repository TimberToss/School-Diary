package com.example.schooldiary.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.schooldiary.R
import com.example.schooldiary.databinding.ActivityMainBinding
import com.example.schooldiary.ui.registration.RegistrationActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (FirebaseAuth.getInstance().currentUser == null) {
            startRegistrationActivity()
        }

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_news, R.id.navigation_diary, R.id.navigation_marks,
                R.id.navigation_more, R.id.navigation_call_schedule,
                R.id.navigation_holidays_schedule, R.id.navigation_final_grades,
                R.id.show_news_fragment, R.id.show_news_fragment, R.id.navigation_settings)
                .build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

    private fun startRegistrationActivity() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
        finish()
    }
}