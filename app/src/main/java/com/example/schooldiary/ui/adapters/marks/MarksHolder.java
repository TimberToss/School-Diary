package com.example.schooldiary.ui.adapters.marks;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
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

        TextView name = getItemView().findViewById(R.id.subject_name);
        TextView marks = getItemView().findViewById(R.id.subject_marks);

        name.setText(mark.getName());
        marks.setText(mark.getMarks());
    }
}
