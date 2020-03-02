package com.example.schooldiary.mainpage.bottomNavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.mainpage.model.News;
import com.example.schooldiary.mainpage.ui.FirestoreRecyclerAdapter;
import com.example.schooldiary.mainpage.ui.FirestoreRecyclerOptions;
import com.example.schooldiary.mainpage.ui.NewsHolder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class NewsFragment extends Fragment {

    private RecyclerView newsRecyclerView;
    private FirestoreRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        newsRecyclerView = root.findViewById(R.id.news_recycler_view);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        Query query = firestore.collection("news")
                .orderBy("serialNumber", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<News> options = new FirestoreRecyclerOptions.Builder<News>()
                .setQuery(query, News.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<News, NewsHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull NewsHolder holder, int position, @NonNull News model) {
                holder.bindData(model);
            }

            @NonNull
            @Override
            public NewsHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.item_news for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.item_news, group, false);

                return new NewsHolder(view);
            }
        };

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
