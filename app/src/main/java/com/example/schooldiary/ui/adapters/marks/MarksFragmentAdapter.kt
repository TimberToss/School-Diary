package com.example.schooldiary.ui.adapters.marks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.schooldiary.R
import com.example.schooldiary.model.marks.Marks
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class MarksFragmentAdapter(options: FirestoreRecyclerOptions<Marks>)
    : FirestoreRecyclerAdapter<Marks, MarksHolder>(options) {

    override fun onCreateViewHolder(group: ViewGroup, i: Int): MarksHolder {
        val itemView = LayoutInflater.from(group.context)
                .inflate(R.layout.item_mark, group, false)
        return MarksHolder(itemView)
    }

    public override fun onBindViewHolder(holder: MarksHolder, position: Int, model: Marks) {
        holder.bindData(model)
    }
}