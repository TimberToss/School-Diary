package com.example.schooldiary.mainpage;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CheckLogInUser {
    private static CheckLogInUser instance;

    private CheckLogInUser(){}

    public static CheckLogInUser getInstance(){
        if (instance == null) {
            instance = new CheckLogInUser();
        }
        return instance;
    }

    public boolean isLoggedInUser(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        return currentUser != null;
    }
}
