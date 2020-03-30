package com.example.schooldiary.ui.adapters.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.schooldiary.R;
import com.example.schooldiary.databinding.ItemNewsBinding;
import com.example.schooldiary.model.News;


public class NewsHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private NewsClickListener listener;

    public interface NewsClickListener {
        void openFragment(int id, String newsPhoto, String time, String newsTitle, String text);
    }

    public NewsHolder(View itemView, NewsClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        this.listener = listener;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(News news) {

        ItemNewsBinding binding = ItemNewsBinding.bind(itemView);
        ImageView photo = binding.newsItemPhoto;
        TextView title = binding.newsItemTitle;
        TextView date = binding.newsItemDate;

        Glide.with(photo.getContext())
                .load(news.getPhoto())
                .into(photo);

        title.setText(news.getTitle());
        date.setText(news.getTime());

        getItemView().setOnClickListener(view -> listener.openFragment(R.id.show_news_fragment,
                news.getPhoto(), news.getTime(), news.getTitle(), news.getText()));
    }
}
