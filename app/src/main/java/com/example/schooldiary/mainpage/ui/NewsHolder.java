package com.example.schooldiary.mainpage.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.schooldiary.R;
import com.example.schooldiary.mainpage.model.News;


public class NewsHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private NewsClickListener listener;
    private ImageView photo;
    private TextView title;

    public interface NewsClickListener {
        void openFragment(int id, String newsPhoto, String newsTitle, String text);
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

        photo = getItemView().findViewById(R.id.news_item_photo);
        title = getItemView().findViewById(R.id.news_item_title);

        Glide.with(photo.getContext())
                .load(news.getPhoto())
                .into(photo);

        title.setText(news.getTitle());

        getItemView().setOnClickListener(view -> listener.openFragment(R.id.show_news_fragment,
                news.getPhoto(), news.getTitle(), news.getText()));
    }
}
