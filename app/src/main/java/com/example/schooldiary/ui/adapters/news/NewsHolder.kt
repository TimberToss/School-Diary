package com.example.schooldiary.ui.adapters.news

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schooldiary.R
import com.example.schooldiary.databinding.ItemNewsBinding
import com.example.schooldiary.model.news.News

class NewsHolder(itemView: View, private val listener: NewsClickListener) : RecyclerView.ViewHolder(itemView) {

    interface NewsClickListener {
        fun openFragment(id: Int, photo: String, time: String, title: String, text: String)
    }

    fun bindData(news: News) {
        val binding = ItemNewsBinding.bind(itemView)
        if (news.photo != null) {
            binding.photo.let {
                Glide.with(it.context)
                        .load(news.photo)
                        .into(it)
            }
        }
        binding.title.text = news.title ?: UNKNOWN
        binding.date.text = news.time ?: UNKNOWN

        itemView.setOnClickListener {
            listener.openFragment(R.id.show_news_fragment,
                    news.photo ?: UNKNOWN,
                    news.time ?: UNKNOWN,
                    news.title ?: UNKNOWN,
                    news.text ?: UNKNOWN)
        }
    }

    companion object {
        private const val UNKNOWN = "Unknown"
    }
}