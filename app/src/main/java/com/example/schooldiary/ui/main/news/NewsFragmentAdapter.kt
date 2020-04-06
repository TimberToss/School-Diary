package com.example.schooldiary.ui.main.news

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.schooldiary.R
import com.example.schooldiary.model.news.News
import com.example.schooldiary.ui.main.news.NewsHolder.NewsClickListener
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class NewsFragmentAdapter(options: FirestoreRecyclerOptions<News>,
                          private val listener: NewsClickListener)
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