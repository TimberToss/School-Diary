package com.example.schooldiary.ui.main.rings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.databinding.FragmentCallScheduleBinding
import com.example.schooldiary.model.dates.Dates
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
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
        val query = firestore.collection(FIREBASE_RINGS_COLLECTION)
                .orderBy(FIREBASE_SERIAL_NUMBER_FIELD)
        val options = FirestoreRecyclerOptions.Builder<Dates>()
                .setQuery(query, Dates::class.java)
                .build()

        adapter = CallScheduleFragmentAdapter(options)
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

    companion object {
        private const val FIREBASE_SERIAL_NUMBER_FIELD = "serialNumber"
        private const val FIREBASE_RINGS_COLLECTION = "rings"
    }
}