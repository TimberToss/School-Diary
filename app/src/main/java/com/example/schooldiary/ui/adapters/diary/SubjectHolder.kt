package com.example.schooldiary.ui.adapters.diary

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.R
import com.example.schooldiary.databinding.ItemSubjectBinding
import com.example.schooldiary.model.Subject

class SubjectHolder(itemView: View, private val listener: SubjectClickListener) : RecyclerView.ViewHolder(itemView) {

    interface SubjectClickListener {
        fun openFragment(id: Int, name: String, homework: String, classroom: String, teacher: String)
    }

    fun bindData(subject: Subject) {
        val binding = ItemSubjectBinding.bind(itemView)
        binding.startTime.text = calculateTime(subject.serialNumber)
        binding.name.text = subject.name
        binding.classroom.text = "557"
        itemView.setOnClickListener {
            listener.openFragment(R.id.show_subject_fragment,
                    subject.name,
                    subject.homework,
                    binding.classroom.text.toString(),
                    "Birukova Tatyana Leontyevna")
        }
    }

    private fun calculateTime(number: Int): String {
        return when (number) {
            1 -> "08:00"
            2 -> "08:55"
            3 -> "09:50"
            4 -> "10:55"
            5 -> "12:00"
            6 -> "13:00"
            7 -> "13:55"
            8 -> "14:50"
            else -> "00:00"
        }
    }
}