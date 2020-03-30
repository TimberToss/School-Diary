package com.example.schooldiary.ui.adapters.diary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.databinding.ItemSubjectBinding;
import com.example.schooldiary.model.Subject;

public class SubjectHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public SubjectHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(Subject subject) {

        ItemSubjectBinding binding = ItemSubjectBinding.bind(getItemView());
        TextView startTime = binding.subjectStartTime;
        TextView subjectName = binding.subjectName;
        TextView subjectAuditory = binding.subjectAuditory;


        startTime.setText(calculateTime(subject.getSerialNumber()));
        subjectName.setText(subject.getName());
        subjectAuditory.setText("238");
    }

    private String calculateTime(int number) {
        switch (number) {
            case 1:
                return "08:00";
            case 2:
                return "08:55";
            case 3:
                return "09:50";
            case 4:
                return "10:55";
            case 5:
                return "12:00";
            case 6:
                return "13:00";
            case 7:
                return "13:55";
            case 8:
                return "14:50";
            default:
                return "00:00";
        }
    }
}
