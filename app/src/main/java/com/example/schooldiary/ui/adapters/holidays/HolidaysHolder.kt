package com.example.schooldiary.ui.adapters.holidays

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.databinding.ItemHolidaysBinding
import com.example.schooldiary.model.Dates

class HolidaysHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(dates: Dates) {
        val date = dates.time?.split(" ") ?: listOf("unknown", "", "unknown")
        val binding = ItemHolidaysBinding.bind(itemView)
        binding.name.text = dates.name
        binding.holidaysStart.text = date[0]
        binding.holidaysEnd.text = date[2]
    }
}