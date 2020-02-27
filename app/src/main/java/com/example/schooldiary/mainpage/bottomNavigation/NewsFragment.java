package com.example.schooldiary.mainpage.bottomNavigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.schooldiary.R;
import com.example.schooldiary.mainpage.data.GlideApp;
import com.example.schooldiary.mainpage.model.News;
import com.example.schooldiary.mainpage.ui.FirestoreRecyclerAdapter;
import com.example.schooldiary.mainpage.ui.FirestoreRecyclerOptions;
import com.example.schooldiary.mainpage.ui.NewsHolder;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private FirebaseFirestore firestore;
    private Query query;
    private RecyclerView newsRecyclerView;
    private FirestoreRecyclerAdapter adapter;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        // Reference to an image file in Cloud Storage
//        StorageReference storageReference = storage.getReferenceFromUrl("gs://school-diary-83cee.appspot.com/school.jpg");
//
//// ImageView in your Activity
//        ImageView imageView = getActivity().findViewById(R.id.news_image);
//
//// Download directly from StorageReference using Glide
//// (See MyAppGlideModule for Loader registration)
//        GlideApp.with(getActivity())
//                .load(storageReference)
//                .into(imageView);

        firestore = FirebaseFirestore.getInstance();
        query = firestore.collection("news")
                .orderBy("serialNumber", Query.Direction.DESCENDING);

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("Fire", "All bad");
                    return;
                }

                // Convert query snapshot to a list of news
                List<News> listOfNews = snapshot.toObjects(News.class);

                // Update UI
                // ...
            }
        });

        FirestoreRecyclerOptions<News> options = new FirestoreRecyclerOptions.Builder<News>()
                .setQuery(query, News.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<News, NewsHolder>(options) {
            @Override
            public void onBindViewHolder(NewsHolder holder, int position, News model) {
                holder.bindData(model);
            }

            @Override
            public NewsHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.iem_news, group, false);

                return new NewsHolder(view);
            }
        };

        newsRecyclerView = getActivity().findViewById(R.id.news_recycler_view);
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
