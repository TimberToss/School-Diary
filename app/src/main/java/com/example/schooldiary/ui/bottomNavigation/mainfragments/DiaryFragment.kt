package com.example.schooldiary.ui.bottomNavigation.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentDiaryBinding
import com.example.schooldiary.model.day.Day
import com.example.schooldiary.model.subject.Subject
import com.example.schooldiary.ui.adapters.diary.DayHolder
import com.example.schooldiary.ui.adapters.diary.DiaryFragmentAdapter
import com.example.schooldiary.ui.adapters.diary.SubjectHolder.SubjectClickListener
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class DiaryFragment : Fragment(), SubjectClickListener {
    private var _binding: FragmentDiaryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FirestoreRecyclerAdapter<Day, DayHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection("days")
                .orderBy("serialNumber", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Day>()
                .setQuery(query, Day::class.java)
                .build()
        adapter = DiaryFragmentAdapter(options, this)

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

    override fun openFragment(id: Int, name: String, homework: String, classroom: String, teacher: String) {
        val bundle = bundleOf("subject" to Subject(name, homework, teacher, classroom))
        val navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)
        navController.navigate(id, bundle)
    }
}