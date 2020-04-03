package com.example.schooldiary.ui.adapters.marks

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.databinding.ItemMarkBinding
import com.example.schooldiary.model.marks.Marks
import kotlin.math.roundToInt

class MarksHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(mark: Marks) {
        val binding = ItemMarkBinding.bind(itemView)
        binding.name.text = mark.name
        binding.marks.text = mark.marks ?: "0"
        binding.averageMark.text = calculateAverage(mark.marks ?: "0")
    }

    private fun calculateAverage(str: String): String {
        var sum = 0.0
        for (element in str) {
            sum += element.toString().toDouble()
        }
        sum = (sum / str.length * 100.0).roundToInt() / 100.0
        return sum.toString()
    }
}