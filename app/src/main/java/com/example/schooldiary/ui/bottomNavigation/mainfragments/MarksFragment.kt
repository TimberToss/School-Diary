package com.example.schooldiary.ui.bottomNavigation.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentMarksBinding
import com.example.schooldiary.model.marks.Marks
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions
import com.example.schooldiary.ui.adapters.marks.MarksHolder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MarksFragment : Fragment() {
    private var _binding: FragmentMarksBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FirestoreRecyclerAdapter<Marks, MarksHolder>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection("subjects")
                .orderBy("name", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Marks>()
                .setQuery(query, Marks::class.java)
                .build()

        adapter = object : FirestoreRecyclerAdapter<Marks, MarksHolder>(options) {
            override fun onCreateViewHolder(group: ViewGroup, i: Int): MarksHolder {
                val itemView = LayoutInflater.from(group.context)
                        .inflate(R.layout.item_mark, group, false)
                return MarksHolder(itemView)
            }

            public override fun onBindViewHolder(holder: MarksHolder, position: Int, model: Marks) {
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