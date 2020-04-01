package com.example.schooldiary.ui.bottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schooldiary.databinding.FragmentShowSubjectBinding

class ShowSubjectFragment : Fragment() {
    private var _binding: FragmentShowSubjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentShowSubjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        if (bundle != null) {
            val name = bundle.getString("Name")
            binding.name.text = name
            val homework = bundle.getString("Homework")
            binding.homework.text = homework
            val teacher = bundle.getString("Teacher")
            binding.teacher.text = teacher
            val classroom = bundle.getString("Classroom")
            binding.classroom.text = classroom
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}