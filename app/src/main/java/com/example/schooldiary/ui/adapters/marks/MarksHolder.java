package com.example.schooldiary.ui.adapters.marks;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.databinding.ItemMarkBinding;
import com.example.schooldiary.model.Marks;

public class MarksHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public MarksHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(Marks mark) {

        ItemMarkBinding binding = ItemMarkBinding.bind(itemView);
        TextView name = binding.subjectName;
        TextView marks = binding.subjectMarks;
        TextView average = binding.subjectAverageMark;

        name.setText(mark.getName());
        marks.setText(mark.getMarks());
        average.setText(calculateAverage(mark.getMarks()));
    }

    private String calculateAverage(String str) {
        double sum = 0.0;
        for (int i = 0; i < str.length(); i++) {
            sum += Double.parseDouble(String.valueOf(str.charAt(i)));
        }
        sum = Math.round(sum / str.length() * 100d) / 100d;
        return String.valueOf(sum);
    }
}
