package com.example.schooldiary.ui.main.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentNewsBinding
import com.example.schooldiary.model.dates.Dates
import com.example.schooldiary.model.news.News
import com.example.schooldiary.ui.main.news.NewsHolder.NewsClickListener
import com.example.schooldiary.viewmodel.news.NewsViewModel
import com.example.schooldiary.viewmodel.rings.CallScheduleViewModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NewsFragment : Fragment(), NewsClickListener {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsFragmentAdapter: FirestoreRecyclerAdapter<News, NewsHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: NewsViewModel by viewModels()
        val options = viewModel.getFirestoreRecyclerOptionsWithOrder<News>(
                collectionName = FIREBASE_NEWS_COLLECTION,
                order = FIREBASE_SERIAL_NUMBER_FIELD,
                queryDirection = Query.Direction.DESCENDING)
        newsFragmentAdapter = NewsFragmentAdapter(options, this)

        with(binding.recyclerView) {
            adapter = newsFragmentAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onStart() {
        super.onStart()
        newsFragmentAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        newsFragmentAdapter.stopListening()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openFragment(id: Int, photo: String, time: String, title: String, text: String) {
        val bundle = bundleOf(ARG_NEWS to News(photo, time, title, text))
        activity?.let {
            val navController = Navigation.findNavController(it, R.id.nav_host_fragment)
            navController.navigate(id, bundle)
        }
    }

    companion object {
        private const val FIREBASE_SERIAL_NUMBER_FIELD = "serialNumber"
        private const val FIREBASE_NEWS_COLLECTION = "news"
        private const val ARG_NEWS = "news"
    }
}