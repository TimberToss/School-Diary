package com.example.schooldiary.ui.main.holidays

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.schooldiary.R
import com.example.schooldiary.model.dates.Dates
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class HolidaysFragmentAdapter(options: FirestoreRecyclerOptions<Dates>)
    : FirestoreRecyclerAdapter<Dates, HolidaysHolder>(options) {

    override fun onCreateViewHolder(group: ViewGroup, i: Int): HolidaysHolder {
        val itemView = LayoutInflater.from(group.context)
                .inflate(R.layout.item_holidays, group, false)
        return HolidaysHolder(itemView)
    }

    public override fun onBindViewHolder(holder: HolidaysHolder, position: Int, model: Dates) {
        holder.bindData(model)
    }
}