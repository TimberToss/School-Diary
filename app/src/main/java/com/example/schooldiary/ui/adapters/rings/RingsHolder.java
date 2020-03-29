package com.example.schooldiary.ui.adapters.rings;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Dates;

public class RingsHolder extends RecyclerView.ViewHolder {
    private View itemView;

    public RingsHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(Dates dates) {

        TextView name = getItemView().findViewById(R.id.rings_name);
        TextView time = getItemView().findViewById(R.id.rings_time);

        name.setText(dates.getName());
        time.setText(dates.getTime());
    }
}