package com.example.schooldiary.ui.registration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentLogInBinding
import com.example.schooldiary.ui.MainActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding
        get() = _binding!!
    //I create this property 'cause another thread could have changed the value of _binding to null
    //between initialization and return binding.root in onCreateView.

    private lateinit var statusTextView: TextView
    private lateinit var detailTextView: TextView
    private lateinit var emailField: TextInputEditText
    private lateinit var passwordField: TextInputEditText

    @VisibleForTesting
    private lateinit var progressBar: ProgressBar

    private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        // this function invokes after onViewCreated where auth initializes
        updateUI(currentUser)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _binding = FragmentLogInBinding.bind(view)
        statusTextView = binding.signInDetailStatus

        detailTextView = binding.signInDetailStatus
        emailField = binding.signInTextInputEmail
        passwordField = binding.signInTextInputPassword
        progressBar = binding.signInProgressBar
        // hide here, otherwise bar will spin
        hideProgressBar()

        mAuth = FirebaseAuth.getInstance()

        val signUp = binding.signInBtn2
        signUp.setOnClickListener {
            signInWithEmailAndPassword(view, emailField.text.toString(), passwordField.text.toString())
        }
    }

    private fun signInWithEmailAndPassword(view: View, email: String, password: String) {
        Log.d(TAG, "signAccount:$email")
        if (!validateForm()) {
            return
        }
        showProgressBar()

        // [START create_user_with_email]
        GlobalScope.launch(Dispatchers.IO) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task: Task<AuthResult?> ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = mAuth.currentUser
                            GlobalScope.launch(Dispatchers.Main) {
                                updateUI(user)
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            GlobalScope.launch(Dispatchers.Main) {
                                Toast.makeText(view.context, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful) {
                            GlobalScope.launch(Dispatchers.Main) {
                                statusTextView.setText(R.string.auth_failed)
                            }
                        }
                        // when auth is fail hide on time, but when auth is success hide much earlier than a new activity begins
//                        Log.d(TAG,"mAuth")
//                        hideProgressBar()
                    }
        }
        // [END sign_in_with_email]
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun validateForm(): Boolean {
        var valid = true
        val email = emailField.text.toString()
        if (TextUtils.isEmpty(email)) {
            emailField.error = "Required."
            valid = false
        } else {
            emailField.error = null
        }
        val password = passwordField.text.toString()
        if (TextUtils.isEmpty(password)) {
            passwordField.error = "Required."
            valid = false
        } else {
            passwordField.error = null
        }
        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val activity: Activity? = activity
            val intent = Intent(activity, MainActivity::class.java)
            Log.d(TAG,"updateUI")
//            hideProgressBar()
            startActivity(intent)
            activity!!.finish()
        } else {
            statusTextView.setText(R.string.signed_out)
            detailTextView.text = null
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}