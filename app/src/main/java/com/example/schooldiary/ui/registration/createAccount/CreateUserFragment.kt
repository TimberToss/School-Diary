package com.example.schooldiary.ui.registration.createAccount

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
import com.example.schooldiary.databinding.FragmentCreateUserBinding
import com.example.schooldiary.ui.MainActivity
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateUserFragment : Fragment() {
    private var _binding: FragmentCreateUserBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var statusTextView: TextView
    private lateinit var detailTextView: TextView
    private lateinit var emailField: TextInputEditText
    private lateinit var passwordField: TextInputEditText

    @VisibleForTesting
    private lateinit var progressBar: ProgressBar

    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _binding = FragmentCreateUserBinding.bind(view)

        statusTextView = binding.registrationEmailPasswordStatus
        detailTextView = binding.registrationDetailStatus
        emailField = binding.registrationTextInputEmail
        passwordField = binding.registrationTextInputPassword
        progressBar = binding.registrationProgressBar
        // hide here, otherwise bar will spin
        hideProgressBar()


        auth = FirebaseAuth.getInstance()

        val signUp = binding.signUpBtn2
        signUp.setOnClickListener {
            createAccount(view, emailField.text.toString(), passwordField.text.toString())
        }
    }

    private fun createAccount(view: View, email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }
        showProgressBar()

        GlobalScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task: Task<AuthResult?> ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            GlobalScope.launch(Dispatchers.Main) {
                                updateUI(user)
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            GlobalScope.launch(Dispatchers.Main) {
                                Toast.makeText(view.context, "Registration failed.",
                                        Toast.LENGTH_SHORT).show()
                            }
                        }

                        // when auth is fail hide on time, but when auth is success hide much earlier than a new activity begins

//                        GlobalScope.launch(Dispatchers.Main) {
//                            hideProgressBar()
//                        }
                    }
        }
    }

    private fun createUser(view: View, email: String, password: String) {

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
            statusTextView.text = getString(R.string.emailpassword_status_fmt,
                    user.email, user.isEmailVerified)
            detailTextView.text = getString(R.string.firebase_status_fmt, user.uid)
            val activity: Activity? = activity
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}