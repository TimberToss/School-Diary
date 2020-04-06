package com.example.schooldiary.ui.bottomnavigation.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentFinalGradesBinding
import com.example.schooldiary.model.marks.Marks
import com.example.schooldiary.ui.adapters.finalgrades.FinalGradesFragmentAdapter
import com.example.schooldiary.ui.adapters.finalgrades.FinalGradesHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
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

        adapter = FinalGradesFragmentAdapter(options)
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