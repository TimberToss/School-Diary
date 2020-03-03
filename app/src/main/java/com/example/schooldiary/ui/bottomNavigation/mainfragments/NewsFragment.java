package com.example.schooldiary.ui.bottomNavigation.mainfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.News;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerAdapter;
import com.example.schooldiary.ui.adapters.firestorerecycler.FirestoreRecyclerOptions;
import com.example.schooldiary.ui.adapters.news.NewsFragmentAdapter;
import com.example.schooldiary.ui.adapters.news.NewsHolder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class NewsFragment extends Fragment implements NewsHolder.NewsClickListener {

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

        adapter = new NewsFragmentAdapter(options, this);

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

    @Override
    public void openFragment(int id, String photo, String title, String text) {
        Bundle bundle = new Bundle();
        bundle.putString("Photo", photo);
        bundle.putString("Title", title);
        bundle.putString("Text", text);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(id, bundle);
    }
}
