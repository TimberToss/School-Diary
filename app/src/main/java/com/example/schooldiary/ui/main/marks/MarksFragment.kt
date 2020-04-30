package com.example.schooldiary.ui.main.marks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.databinding.FragmentMarksBinding
import com.example.schooldiary.model.marks.Marks
import com.example.schooldiary.viewmodel.marks.MarksViewModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter

class MarksFragment : Fragment() {
    private var _binding: FragmentMarksBinding? = null
    private val binding get() = _binding!!
    private lateinit var marksFragmentAdapter: FirestoreRecyclerAdapter<Marks, MarksHolder>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: MarksViewModel by viewModels()
        val options = viewModel.getFirestoreRecyclerOptions<Marks>(FIREBASE_SUBJECTS_COLLECTION)
        marksFragmentAdapter = MarksFragmentAdapter(options)

        with(binding.recyclerView) {
            adapter = marksFragmentAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onStart() {
        super.onStart()
        marksFragmentAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        marksFragmentAdapter.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val FIREBASE_SUBJECTS_COLLECTION = "subjects"
    }
}