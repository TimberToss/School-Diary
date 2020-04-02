package com.example.schooldiary.ui.bottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.schooldiary.databinding.FragmentShowNewsBinding
import com.example.schooldiary.model.News

class ShowNewsFragment : Fragment() {
    private var _binding: FragmentShowNewsBinding? = null
    private val binding get() = _binding!!

    private var news: News? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentShowNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            news = it.getParcelable(ARGS_NEWS)
        }

        news?.let {
            if (it.photo != UNKNOWN) {
                Glide.with(binding.photo.context)
                        .load(it.photo)
                        .into(binding.photo)
            }
            binding.time.text = it.time
            binding.title.text = it.title
            binding.text.text = it.text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val UNKNOWN = "Unknown"
        private const val ARGS_NEWS = "news"
    }
}