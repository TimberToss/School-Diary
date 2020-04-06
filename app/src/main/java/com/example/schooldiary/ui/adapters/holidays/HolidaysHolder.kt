package com.example.schooldiary.ui.adapters.holidays

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.databinding.ItemHolidaysBinding
import com.example.schooldiary.model.dates.Dates

class HolidaysHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val possibleDates: Set<String> = setOf("Winter", "Spring", "Summer", "Autumn")

    fun bindData(dates: Dates) {
        val date = dates.time.split(" ")
        val binding = ItemHolidaysBinding.bind(itemView)
        if (dates.name in possibleDates) {
            binding.name.text = dates.name
        }
        if (date.size <= 3) {
            binding.holidaysStart.text = date[0]
            binding.holidaysEnd.text = date[2]
        }
    }
}