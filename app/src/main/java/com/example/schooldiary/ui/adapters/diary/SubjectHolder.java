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
        TextView subjectSerialNumber = binding.subjectSerialNumber;
        TextView subjectName = binding.subjectName;
        TextView subjectHomework = binding.subjectHomework;
        TextView subjectAuditory = binding.subjectAuditory;


        subjectSerialNumber.setText(String.format("%d.", subject.getSerialNumber()));
        subjectName.setText(subject.getName());
        subjectHomework.setText(subject.getHomework());
        subjectAuditory.setText("238");
    }
}
