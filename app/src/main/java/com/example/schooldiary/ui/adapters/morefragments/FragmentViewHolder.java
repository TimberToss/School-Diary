package com.example.schooldiary.ui.adapters.morefragments;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.databinding.ItemMoreFragmentsBinding;
import com.example.schooldiary.model.ItemFragment;

public class FragmentViewHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private StudentsClickListener listener;

    public interface StudentsClickListener {
        void openFragment(int id);
    }

    public FragmentViewHolder(@NonNull View itemView, StudentsClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        this.listener = listener;
    }

    public void bindData(ItemFragment itemFragment) {

        ItemMoreFragmentsBinding binding = ItemMoreFragmentsBinding.bind(itemView);
        ImageView iconFragment = binding.iconFragment;
        TextView nameFragment = binding.nameFragment;
        iconFragment.setImageDrawable(itemFragment.getIconFragment());
        nameFragment.setText(itemFragment.getTitleFragment());

        itemView.setOnClickListener(view -> listener.openFragment(itemFragment.getId()));

    }
}
