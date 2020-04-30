package com.example.schooldiary.ui.main.finalgrades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.databinding.FragmentFinalGradesBinding
import com.example.schooldiary.model.marks.Marks
import com.example.schooldiary.viewmodel.finalgrades.FinalGradesViewModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FinalGradesFragment : Fragment() {
    private var _binding: FragmentFinalGradesBinding? = null
    private val binding get() = _binding!!
    private lateinit var finalGradesFragmentAdapter:
            FirestoreRecyclerAdapter<Marks, FinalGradesHolder>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFinalGradesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: FinalGradesViewModel by viewModels()
        val options = viewModel.getFirestoreRecyclerOptions<Marks>(FIREBASE_FINAL_GRADES_COLLECTION)
        finalGradesFragmentAdapter = FinalGradesFragmentAdapter(options)

        with(binding.recyclerView) {
            adapter = finalGradesFragmentAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onStart() {
        super.onStart()
        finalGradesFragmentAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        finalGradesFragmentAdapter.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        private const val FIREBASE_FINAL_GRADES_COLLECTION = "finalGrades"
    }
}