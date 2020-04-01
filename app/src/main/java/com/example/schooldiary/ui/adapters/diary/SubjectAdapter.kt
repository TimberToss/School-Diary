package com.example.schooldiary.ui.adapters.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.R
import com.example.schooldiary.model.Subject

class SubjectAdapter(private val subjects: List<Subject>, private val listener: SubjectHolder.SubjectClickListener)
    : RecyclerView.Adapter<SubjectHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_subject, parent, false)
        return SubjectHolder(view, listener)
    }

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) {
        holder.bindData(subjects[position])
    }

    override fun getItemCount(): Int {
        return subjects.size
    }
}
