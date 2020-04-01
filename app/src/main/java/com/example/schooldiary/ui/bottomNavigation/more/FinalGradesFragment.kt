package com.example.schooldiary.ui.bottomNavigation.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentFinalGradesBinding
import com.example.schooldiary.model.Marks
import com.example.schooldiary.ui.adapters.finalGrades.FinalGradesHolder
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FinalGradesFragment : Fragment() {
    private var _binding: FragmentFinalGradesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FirestoreRecyclerAdapter<Marks, FinalGradesHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFinalGradesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firestore = FirebaseFirestore.getInstance()
        val query: Query = firestore.collection("finalGrades")
        val options = FirestoreRecyclerOptions.Builder<Marks>()
                .setQuery(query, Marks::class.java)
                .build()

        adapter = object : FirestoreRecyclerAdapter<Marks, FinalGradesHolder>(options) {
            override fun onCreateViewHolder(group: ViewGroup, i: Int): FinalGradesHolder {
                val itemView = LayoutInflater.from(group.context)
                        .inflate(R.layout.item_final_grade, group, false)
                return FinalGradesHolder(itemView)
            }

            public override fun onBindViewHolder(holder: FinalGradesHolder, position: Int, model: Marks) {
                holder.bindData(model)
            }
        }

        binding.recyclerView.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}