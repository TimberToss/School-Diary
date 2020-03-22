package com.example.schooldiary.ui.registration.createAccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schooldiary.R;
import com.example.schooldiary.ui.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class CreateUserFragment extends Fragment {

    private static final String TAG = "EmailPassword";

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private TextInputEditText mEmailField;
    private TextInputEditText mPasswordField;

    @VisibleForTesting
    public ProgressBar mProgressBar;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Views
        mStatusTextView = getActivity().findViewById(R.id.registration_email_password_status);
        mDetailTextView = getActivity().findViewById(R.id.registration_detail_status);
        mEmailField = getActivity().findViewById(R.id.registration_text_input_email);
        mPasswordField = getActivity().findViewById(R.id.registration_text_input_password);
        setProgressBar(R.id.registration_progress_bar);
        // hide here, otherwise bar will spin
        hideProgressBar();


        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        Button signUp = view.findViewById(R.id.sign_up_btn_2);
        signUp.setOnClickListener(v -> {
            createAccount(view, mEmailField.getText().toString(), mPasswordField.getText().toString());

        });
    }

    private void createAccount(View view, String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressBar();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(view.getContext(), "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        // when auth is fail hide on time, but when auth is success hide much earlier than a new activity begins
                        hideProgressBar();
                        // [END_EXCLUDE]
                });
        // [END create_user_with_email]
    }

    public void setProgressBar(int resId) {
        mProgressBar = getActivity().findViewById(resId);
    }

    public void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            Activity activity = getActivity();
            Intent intent = new Intent(activity, MainActivity.class);
            startActivity(intent);
            activity.finish();

        }
    }
}
