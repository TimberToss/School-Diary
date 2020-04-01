package com.example.schooldiary.ui.bottomNavigation.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentCallScheduleBinding
import com.example.schooldiary.model.Dates
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions
import com.example.schooldiary.ui.adapters.rings.SchoolCallHolder
import com.google.firebase.firestore.FirebaseFirestore

class CallScheduleFragment : Fragment() {
    private var _binding: FragmentCallScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FirestoreRecyclerAdapter<Dates, SchoolCallHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCallScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection("rings")
                .orderBy("serialNumber")
        val options = FirestoreRecyclerOptions.Builder<Dates>()
                .setQuery(query, Dates::class.java)
                .build()

        adapter = object : FirestoreRecyclerAdapter<Dates, SchoolCallHolder>(options) {
            override fun onCreateViewHolder(group: ViewGroup, i: Int): SchoolCallHolder {
                val itemView = LayoutInflater.from(group.context)
                        .inflate(R.layout.item_school_call, group, false)
                return SchoolCallHolder(itemView)
            }

            public override fun onBindViewHolder(holder: SchoolCallHolder, position: Int, model: Dates) {
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