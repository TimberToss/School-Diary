package com.example.schooldiary.ui.main.finalgrades

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.databinding.ItemFinalGradeBinding
import com.example.schooldiary.model.marks.Marks
import java.util.*

class FinalGradesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(mark: Marks) {
        val binding = ItemFinalGradeBinding.bind(itemView)
        val views: MutableList<TextView> = ArrayList()
        views.add(binding.firstMark)
        views.add(binding.secondMark)
        views.add(binding.thirdMark)
        views.add(binding.forthMark)
        views.add(binding.fifthMark)

        binding.name.text = mark.name

        val grades = mark.marks.toCharArray()
        for (i in 0..4) {
            if (i < grades.size) {
                views[i].text = grades[i].toString()
            } else {
                views[i].text = " "
            }
        }
    }
}