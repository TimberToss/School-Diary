package com.example.schooldiary.ui.main.rings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.databinding.FragmentCallScheduleBinding
import com.example.schooldiary.model.dates.Dates
import com.example.schooldiary.ui.main.holidays.HolidaysScheduleFragment
import com.example.schooldiary.viewmodel.holidays.HolidaysViewModel
import com.example.schooldiary.viewmodel.rings.CallScheduleViewModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class CallScheduleFragment : Fragment() {
    private var _binding: FragmentCallScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var callScheduleFragmentAdapter:
            FirestoreRecyclerAdapter<Dates, SchoolCallHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCallScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: CallScheduleViewModel by viewModels()
        val options = viewModel.getFirestoreRecyclerOptionsWithOrder<Dates>(
                collectionName = FIREBASE_RINGS_COLLECTION,
                order = FIREBASE_SERIAL_NUMBER_FIELD)
        callScheduleFragmentAdapter = CallScheduleFragmentAdapter(options)

        with(binding.recyclerView) {
            adapter = callScheduleFragmentAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onStart() {
        super.onStart()
        callScheduleFragmentAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        callScheduleFragmentAdapter.stopListening()
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