package com.example.schooldiary.ui.adapters.news

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.schooldiary.R
import com.example.schooldiary.model.News
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions
import com.example.schooldiary.ui.adapters.news.NewsHolder.NewsClickListener


class NewsFragmentAdapter(options: FirestoreRecyclerOptions<News>, private val listener: NewsClickListener)
    : FirestoreRecyclerAdapter<News, NewsHolder>(options) {

    override fun onCreateViewHolder(group: ViewGroup, i: Int): NewsHolder {
        val view = LayoutInflater.from(group.context)
                .inflate(R.layout.item_news, group, false)
        return NewsHolder(view, listener)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int, model: News) {
        holder.bindData(model)
    }
}