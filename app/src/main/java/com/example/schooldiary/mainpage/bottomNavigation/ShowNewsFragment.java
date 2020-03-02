package com.example.schooldiary.mainpage.bottomNavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.schooldiary.R;

public class ShowNewsFragment extends Fragment {

    private ImageView newsPhoto;
    private TextView newsTitle;
    private TextView newsText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_show_news, container, false);
        newsPhoto = root.findViewById(R.id.news_photo);
        newsTitle = root.findViewById(R.id.news_title);
        newsText = root.findViewById(R.id.news_text);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {

            String photo = bundle.getString("Photo");
            Glide.with(newsPhoto.getContext())
                    .load(photo)
                    .into(newsPhoto);

            String title = bundle.getString("Title");
            newsTitle.setText(title);

            String text = bundle.getString("Text");
            newsText.setText(text);

        }
    }
}
