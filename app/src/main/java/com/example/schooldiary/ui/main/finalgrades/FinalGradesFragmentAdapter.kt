package com.example.schooldiary.ui.main.finalgrades

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.schooldiary.R
import com.example.schooldiary.model.marks.Marks
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class FinalGradesFragmentAdapter(options: FirestoreRecyclerOptions<Marks>)
    : FirestoreRecyclerAdapter<Marks, FinalGradesHolder>(options) {

    override fun onCreateViewHolder(group: ViewGroup, i: Int): FinalGradesHolder {
        val itemView = LayoutInflater.from(group.context)
                .inflate(R.layout.item_final_grade, group, false)
        return FinalGradesHolder(itemView)
    }

    public override fun onBindViewHolder(holder: FinalGradesHolder, position: Int, model: Marks) {
        holder.bindData(model)
    }
}