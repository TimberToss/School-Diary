package com.example.schooldiary.ui.bottomnavigation.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)
        binding.callSchedule.setOnClickListener{
            navController.navigate(R.id.navigation_call_schedule)
        }
        binding.holidaySchedule.setOnClickListener{
            navController.navigate(R.id.navigation_holidays_schedule)
        }
        binding.finalGrades.setOnClickListener{
            navController.navigate(R.id.navigation_final_grades)
        }
        binding.settings.setOnClickListener{
            navController.navigate(R.id.navigation_settings)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}