package com.example.schooldiary.ui.adapters.diary

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.databinding.ItemDayBinding
import com.example.schooldiary.model.day.Day
import com.example.schooldiary.model.subject.Subject
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source

class DayHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(day: Day, listener: SubjectHolder.SubjectClickListener) {
        val binding = ItemDayBinding.bind(itemView)
        day.name?.let {
            binding.name.text = StringBuilder().append(day.name).append(", 16 September").toString()
            val query = FirebaseFirestore.getInstance()
                    .collection("days")
                    .document(it)
                    .collection("subjects")
                    .orderBy("serialNumber")

            query.get(Source.CACHE) // cache don't disappear by clear cache, but by clear data of app
                    .addOnCompleteListener { task ->
                        val querySnapshot = task.result
                        if (!querySnapshot?.isEmpty!!) {
                            inflateAdapter(querySnapshot, binding.recyclerView, listener)
                        } else {
                            query.get(Source.SERVER)
                                    .addOnCompleteListener { newTask ->
                                        val documentSnapshot = newTask.result
                                        if (documentSnapshot != null) {
                                            inflateAdapter(documentSnapshot, binding.recyclerView, listener)
                                        }
                                    }
                        }
                    }
        }
    }

    private fun inflateAdapter(snapshot: QuerySnapshot, recyclerView: RecyclerView,
                               listener: SubjectHolder.SubjectClickListener) {
        val subjects: List<Subject> = snapshot.toObjects(Subject::class.java)
        val adapter = SubjectAdapter(subjects, listener)
        recyclerView.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(recyclerView.context)
            it.isNestedScrollingEnabled = false
        }
    }
}