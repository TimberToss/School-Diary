package com.example.schooldiary.mainpage.ui;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.mainpage.model.Marks;

public class MarksHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private TextView subjectName;
    private TextView marks;

    public MarksHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(Marks mark) {

        subjectName = getItemView().findViewById(R.id.subject_name);
        marks = getItemView().findViewById(R.id.subject_marks);

        Log.d("Fire",mark.getMarks());
        Log.d("Fire",mark.getSubjectName());
        subjectName.setText(mark.getMarks());
        marks.setText(mark.getSubjectName());
    }
}
