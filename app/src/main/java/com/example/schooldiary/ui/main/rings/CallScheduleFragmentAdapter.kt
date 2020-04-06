package com.example.schooldiary.ui.main.rings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.schooldiary.R
import com.example.schooldiary.model.dates.Dates
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class CallScheduleFragmentAdapter(options: FirestoreRecyclerOptions<Dates>)
    : FirestoreRecyclerAdapter<Dates, SchoolCallHolder>(options) {

    override fun onCreateViewHolder(group: ViewGroup, i: Int): SchoolCallHolder {
        val itemView = LayoutInflater.from(group.context)
                .inflate(R.layout.item_school_call, group, false)
        return SchoolCallHolder(itemView)
    }

    public override fun onBindViewHolder(holder: SchoolCallHolder, position: Int, model: Dates) {
        holder.bindData(model)
    }
}