package com.example.schooldiary.ui.registration.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentLogInBinding
import com.example.schooldiary.ui.main.MainActivity
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

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
        if (!validateForm()) {
            return
        }
        showProgressBar()

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else { Toast.makeText(view.context,
                            view.context.resources.getString(R.string.authentication_failed),
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
        with(binding.emailEditText) {
            val email = text.toString()
            if (TextUtils.isEmpty(email)) {
                error = context.resources.getString(R.string.required)
                valid = false
            } else {
                error = null
            }
        }

        with(binding.passwordEditText) {
            val password = text.toString()
            if (TextUtils.isEmpty(password)) {
                error = context.resources.getString(R.string.required)
                valid = false
            } else {
                error = null
            }
        }
        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            activity?.let {
                val intent = Intent(it, MainActivity::class.java)
                startActivity(intent)
                it.finish()
            }
        }
    }
}