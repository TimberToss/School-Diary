package com.example.schooldiary.ui.bottomnavigation.mainfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schooldiary.R
import com.example.schooldiary.databinding.FragmentNewsBinding
import com.example.schooldiary.model.news.News
import com.example.schooldiary.ui.adapters.news.NewsFragmentAdapter
import com.example.schooldiary.ui.adapters.news.NewsHolder
import com.example.schooldiary.ui.adapters.news.NewsHolder.NewsClickListener
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NewsFragment : Fragment(), NewsClickListener {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FirestoreRecyclerAdapter<News, NewsHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val firestore = FirebaseFirestore.getInstance()
        val query = firestore.collection("news")
                .orderBy("serialNumber", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<News>()
                .setQuery(query, News::class.java)
                .build()
        adapter = NewsFragmentAdapter(options, this)

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

    override fun openFragment(id: Int, photo: String, time: String, title: String, text: String) {
        val bundle = bundleOf("news" to News(photo, time, title, text))
        val navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)
        navController.navigate(id, bundle)
    }
}