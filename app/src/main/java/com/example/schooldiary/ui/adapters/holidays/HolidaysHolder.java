package com.example.schooldiary.ui.adapters.holidays;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Dates;

public class HolidaysHolder extends RecyclerView.ViewHolder {
    private View itemView;

    public HolidaysHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(Dates dates) {

        String[] date = dates.getTime().split(" ");

        TextView name = getItemView().findViewById(R.id.holidays_name);
        TextView start = getItemView().findViewById(R.id.holidays_start);
        TextView end = getItemView().findViewById(R.id.holidays_end);

        name.setText(dates.getName());
        start.setText(date[0]);
        end.setText(date[2]);
    }
}
