package com.example.schooldiary.ui.bottomnavigation.more

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schooldiary.databinding.FragmentSettingsBinding
import com.example.schooldiary.ui.registration.RegistrationActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity: Activity? = activity
        binding.button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, RegistrationActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}