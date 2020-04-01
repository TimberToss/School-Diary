package com.example.schooldiary.ui.bottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.schooldiary.databinding.FragmentShowNewsBinding

class ShowNewsFragment : Fragment() {
    private var _binding: FragmentShowNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentShowNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        if (bundle != null) {
            val photo = bundle.getString("Photo")
            binding.photo.let {
                Glide.with(it.context)
                        .load(photo)
                        .into(it)
            }
            val time = bundle.getString("Time")
            binding.time.text = time
            val title = bundle.getString("Title")
            binding.title.text = title
            val text = bundle.getString("Text")
            binding.text.text = text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}