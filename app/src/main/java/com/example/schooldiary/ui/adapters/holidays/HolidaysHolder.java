package com.example.schooldiary.ui.adapters.holidays;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.databinding.ItemHolidaysBinding;
import com.example.schooldiary.model.Dates;

public class HolidaysHolder extends RecyclerView.ViewHolder {
    private View itemView;

    public HolidaysHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bindData(Dates dates) {

        String[] date = dates.getTime().split(" ");

        ItemHolidaysBinding binding = ItemHolidaysBinding.bind(itemView);

        TextView name = binding.holidaysName;
        TextView start = binding.holidaysStart;
        TextView end = binding.holidaysEnd;

        name.setText(dates.getName());
        start.setText(date[0]);
        end.setText(date[2]);
    }
}
