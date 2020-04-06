package com.example.schooldiary.ui.bottomnavigation.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentHolidaysScheduleBinding
import com.example.schooldiary.model.dates.Dates
import com.example.schooldiary.ui.adapters.holidays.HolidaysFragmentAdapter
import com.example.schooldiary.ui.adapters.holidays.HolidaysHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class HolidaysScheduleFragment : Fragment() {
    private var _binding: FragmentHolidaysScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FirestoreRecyclerAdapter<Dates, HolidaysHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentHolidaysScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection("holidays")
                .orderBy("serialNumber")
        val options = FirestoreRecyclerOptions.Builder<Dates>()
                .setQuery(query, Dates::class.java)
                .build()

        adapter = HolidaysFragmentAdapter(options)
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