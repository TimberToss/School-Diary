package com.example.schooldiary.ui.main.showsubject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schooldiary.databinding.FragmentShowSubjectBinding
import com.example.schooldiary.model.subject.Subject

class ShowSubjectFragment : Fragment() {
    private var _binding: FragmentShowSubjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentShowSubjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var subject: Subject? = null

        arguments?.let {
            subject = it.getParcelable(ARG_SUBJECT)
        }

        subject?.let{
            binding.name.text = it.name
            binding.homework.text = it.homework
            binding.teacher.text = it.teacher
            binding.classroom.text = it.classroom
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_SUBJECT = "subject"
    }
}