package com.example.schooldiary.ui.adapters.diary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Subject;

public class SubjectHolder extends RecyclerView.ViewHolder{

    private View itemView;

    public SubjectHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(Subject subject) {
        TextView subjectSerialNumber = getItemView().findViewById(R.id.subject_serial_number);
        TextView subjectTime = getItemView().findViewById(R.id.subject_time);
        TextView subjectName = getItemView().findViewById(R.id.subject_name);
        TextView subjectAuditory = getItemView().findViewById(R.id.subject_auditory);


        // these information doesn't caching and permanently loading from firebase
        subjectSerialNumber.setText(Integer.toString(subject.getSerialNumber()));
        subjectTime.setText("09:30 - 10:20");
        subjectName.setText(subject.getName());
        subjectAuditory.setText("238");
    }
}
