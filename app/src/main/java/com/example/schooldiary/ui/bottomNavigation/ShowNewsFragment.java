package com.example.schooldiary.ui.bottomNavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.schooldiary.R;
import com.example.schooldiary.databinding.FragmentShowNewsBinding;


public class ShowNewsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        FragmentShowNewsBinding binding = FragmentShowNewsBinding.bind(view);
        ImageView newsPhoto = binding.newsPhoto;
        TextView newsTime = binding.newsTime;
        TextView newsTitle = binding.newsTitle;
        TextView newsText = binding.newsText;

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            String photo = bundle.getString("Photo");
            Glide.with(newsPhoto.getContext())
                    .load(photo)
                    .into(newsPhoto);

            String time = bundle.getString("Time");
            newsTime.setText(time);

            String title = bundle.getString("Title");
            newsTitle.setText(title);

            String text = bundle.getString("Text");
            newsText.setText(text);

        }
    }
}
