package com.example.schooldiary.ui.registration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.schooldiary.databinding.FragmentLogInBinding
import com.example.schooldiary.ui.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        // this function invokes after onViewCreated where auth initializes
        updateUI(currentUser)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // hide here, otherwise bar will spin
        hideProgressBar()
        auth = FirebaseAuth.getInstance()

        binding.button.setOnClickListener {
            signInWithEmailAndPassword(view,
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun signInWithEmailAndPassword(view: View, email: String, password: String) {
        Log.d(TAG, "signAccount:$email")
        if (!validateForm()) {
            return
        }
        showProgressBar()

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(view.context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        hideProgressBar()
                    }
                }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun validateForm(): Boolean {
        var valid = true
        binding.emailEditText.let {
            val email = it.text.toString()
            if (TextUtils.isEmpty(email)) {
                it.error = "Required."
                valid = false
            } else {
                it.error = null
            }
        }

        binding.passwordEditText.let {
            val password = it.text.toString()
            if (TextUtils.isEmpty(password)) {
                it.error = "Required."
                valid = false
            } else {
                it.error = null
            }
        }
        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val activity: Activity? = activity
            val intent = Intent(activity, MainActivity::class.java)
            Log.d(TAG, "updateUI")
            startActivity(intent)
            activity!!.finish()
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}