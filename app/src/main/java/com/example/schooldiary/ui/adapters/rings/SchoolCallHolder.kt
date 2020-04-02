package com.example.schooldiary.ui.adapters.rings

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.databinding.ItemSchoolCallBinding
import com.example.schooldiary.model.dates.Dates

class SchoolCallHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(dates: Dates) {
        val binding = ItemSchoolCallBinding.bind(itemView)
        binding.name.text = dates.name ?: " "
        binding.time.text = dates.time ?: " "
    }
}