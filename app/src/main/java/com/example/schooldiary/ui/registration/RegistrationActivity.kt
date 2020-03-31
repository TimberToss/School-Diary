package com.example.schooldiary.ui.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schooldiary.R

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.RegistrationTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }
}