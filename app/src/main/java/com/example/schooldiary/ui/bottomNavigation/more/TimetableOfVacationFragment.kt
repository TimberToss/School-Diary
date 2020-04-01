package com.example.schooldiary.ui.bottomNavigation.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentTimetableOfVacationBinding
import com.example.schooldiary.model.Dates
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions
import com.example.schooldiary.ui.adapters.holidays.HolidaysHolder
import com.google.firebase.firestore.FirebaseFirestore

class TimetableOfVacationFragment : Fragment() {
    private var _binding: FragmentTimetableOfVacationBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FirestoreRecyclerAdapter<Dates, HolidaysHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentTimetableOfVacationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection("holidays")
                .orderBy("serialNumber")
        val options = FirestoreRecyclerOptions.Builder<Dates>()
                .setQuery(query, Dates::class.java)
                .build()

        adapter = object : FirestoreRecyclerAdapter<Dates, HolidaysHolder>(options) {

            override fun onCreateViewHolder(group: ViewGroup, i: Int): HolidaysHolder {
                val itemView = LayoutInflater.from(group.context)
                        .inflate(R.layout.item_holidays, group, false)
                return HolidaysHolder(itemView)
            }

            public override fun onBindViewHolder(holder: HolidaysHolder, position: Int, model: Dates) {
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