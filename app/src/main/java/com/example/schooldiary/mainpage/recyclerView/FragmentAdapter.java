package com.example.schooldiary.mainpage.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;

import java.util.List;

public class FragmentAdapter extends RecyclerView.Adapter<FragmentViewHolder> {

    private FragmentViewHolder.StudentsClickListener listener;
    private List<ItemFragment> itemFragments;

    public FragmentAdapter(FragmentViewHolder.StudentsClickListener listener, List<ItemFragment> itemFragments) {
        this.listener = listener;
        this.itemFragments = itemFragments;
    }

    @NonNull
    @Override
    public FragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_more_fragments, parent, false);
        return new FragmentViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FragmentViewHolder holder, int position) {

        holder.bindData(itemFragments.get(position));
    }

    @Override
    public int getItemCount() {
        return itemFragments.size();
    }
}
