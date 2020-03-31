package com.example.schooldiary.ui.adapters.rings;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.databinding.ItemRingBinding;
import com.example.schooldiary.model.Dates;

public class RingsHolder extends RecyclerView.ViewHolder {
    private View itemView;

    public RingsHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bindData(Dates dates) {

        ItemRingBinding binding = ItemRingBinding.bind(itemView);

        TextView name = binding.ringsName;
        TextView time = binding.ringsTime;

        name.setText(dates.getName());
        time.setText(dates.getTime());
    }
}