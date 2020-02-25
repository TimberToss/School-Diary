package com.example.schooldiary.mainpage.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;

public class FragmentViewHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private StudentsClickListener listener;
    private ImageView iconFragment;
    private TextView nameFragment;

    public interface StudentsClickListener {
        void openFragment(int id);
    }

    public FragmentViewHolder(@NonNull View itemView, StudentsClickListener listener) {
        super(itemView);
        this.itemView = itemView;
        this.listener = listener;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(ItemFragment itemFragment) {

        iconFragment = getItemView().findViewById(R.id.icon_fragment);
        nameFragment = getItemView().findViewById(R.id.name_fragment);
        iconFragment.setImageDrawable(itemFragment.getIconFragment());
        nameFragment.setText(itemFragment.getTitleFragment());

        getItemView().setOnClickListener(view -> listener.openFragment(itemFragment.getId()));

    }
}
