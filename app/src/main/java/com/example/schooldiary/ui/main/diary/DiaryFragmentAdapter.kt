package com.example.schooldiary.ui.main.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.schooldiary.R
import com.example.schooldiary.model.day.Day
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class DiaryFragmentAdapter(options: FirestoreRecyclerOptions<Day>,
                           private val listener: SubjectHolder.SubjectClickListener)
    : FirestoreRecyclerAdapter<Day, DayHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_day, parent, false)
        return DayHolder(view)
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int, model: Day) {
        holder.bindData(model, listener)
    }
}
