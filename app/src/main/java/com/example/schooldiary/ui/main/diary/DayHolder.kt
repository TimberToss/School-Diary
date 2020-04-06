package com.example.schooldiary.ui.main.diary

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.R
import com.example.schooldiary.databinding.ItemDayBinding
import com.example.schooldiary.databinding.ItemSubjectBinding
import com.example.schooldiary.model.day.Day
import com.example.schooldiary.model.subject.Subject
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source

class DayHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val possibleDays: Set<String> = setOf("Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday")

    fun bindData(day: Day, listener: SubjectClickListener) {
        val binding = ItemDayBinding.bind(itemView)

        if (day.name in possibleDays) {
            binding.name.text = itemView.context.getString(R.string.day_of_the_week, day.name)

            val query = FirebaseFirestore.getInstance()
                    .collection("days")
                    .document(day.name)
                    .collection("subjects")
                    .orderBy("serialNumber")

            query.get(Source.CACHE) // cache don't disappear by clear cache, but by clear app data
                    .addOnCompleteListener { task ->
                        val querySnapshot = task.result
                        if (querySnapshot != null && !querySnapshot.isEmpty) {
                            inflateAdapter(querySnapshot, listener)
                        } else {
                            query.get(Source.SERVER)
                                    .addOnCompleteListener { newTask ->
                                        val documentSnapshot = newTask.result
                                        if (documentSnapshot != null && !documentSnapshot.isEmpty) {
                                            inflateAdapter(documentSnapshot, listener)
                                        }
                                    }
                        }
                    }
        }

    }

    private fun inflateAdapter(snapshot: QuerySnapshot, listener: SubjectClickListener) {

        val subjects: List<Subject> = snapshot.toObjects(Subject::class.java)
        val parent = ItemDayBinding.bind(itemView).parentLayout
        val views: MutableList<View> = ArrayList()
        for (i in subjects.indices) {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_subject, parent, true)
            bindSubjectData(subjects[i], view, listener)
//            views.add(view)
        }
//        for (i in subjects.indices) {
//            bindSubjectData(subjects[i], views[i], listener)
//        }

//        val adapter = SubjectAdapter(subjects, listener)
//        recyclerView.let {
//            it.adapter = adapter
//            it.layoutManager = LinearLayoutManager(recyclerView.context)
//            it.isNestedScrollingEnabled = false
//        }
    }

    interface SubjectClickListener {
        fun openFragment(id: Int, name: String, homework: String, classroom: String,
                         teacher: String)
    }

    private fun bindSubjectData(subject: Subject, itemView: View, listener: SubjectClickListener) {
        val binding = ItemSubjectBinding.bind(itemView)
        binding.startTime.text = calculateTime(subject.serialNumber)
        binding.name.text = subject.name
        // Firebase still doesn't have the classroom number and teacher's name
        binding.classroom.text = "557"
        itemView.setOnClickListener {
            listener.openFragment(R.id.show_subject_fragment,
                    subject.name,
                    subject.homework,
                    "557",
                    "Birukova Tatyana Leontyevna")
        }
    }

    private fun calculateTime(number: Int): String {
        return when (number) {
            1 -> "08:00"
            2 -> "08:55"
            3 -> "09:50"
            4 -> "10:55"
            5 -> "12:00"
            6 -> "13:00"
            7 -> "13:55"
            8 -> "14:50"
            else -> "00:00"
        }
    }
}