package com.example.schooldiary.ui.main.holidays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.databinding.FragmentHolidaysScheduleBinding
import com.example.schooldiary.model.dates.Dates
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class HolidaysScheduleFragment : Fragment() {
    private var _binding: FragmentHolidaysScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var holidaysFragmentAdapter: FirestoreRecyclerAdapter<Dates, HolidaysHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentHolidaysScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection(FIREBASE_HOLIDAYS_COLLECTION)
                .orderBy(FIREBASE_SERIAL_NUMBER_FIELD)
        val options = FirestoreRecyclerOptions.Builder<Dates>()
                .setQuery(query, Dates::class.java)
                .build()

        holidaysFragmentAdapter = HolidaysFragmentAdapter(options)

        with(binding.recyclerView) {
            adapter = holidaysFragmentAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onStart() {
        super.onStart()
        holidaysFragmentAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        holidaysFragmentAdapter.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FIREBASE_SERIAL_NUMBER_FIELD = "serialNumber"
        private const val FIREBASE_HOLIDAYS_COLLECTION = "holidays"
    }
}