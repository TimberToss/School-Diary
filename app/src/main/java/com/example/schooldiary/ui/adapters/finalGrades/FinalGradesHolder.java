package com.example.schooldiary.ui.adapters.finalGrades;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.R;
import com.example.schooldiary.model.Marks;

public class FinalGradesHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public FinalGradesHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    private View getItemView() {
        return itemView;
    }

    public void bindData(Marks mark) {
        char[] grades = mark.getMarks().toCharArray();

        TextView name = getItemView().findViewById(R.id.subject_name);
        TextView first_mark = getItemView().findViewById(R.id.first_mark);
        TextView second_mark = getItemView().findViewById(R.id.second_mark);
        TextView third_mark = getItemView().findViewById(R.id.third_mark);
        TextView forth_mark = getItemView().findViewById(R.id.forth_mark);
        TextView fifth_mark = getItemView().findViewById(R.id.fifth_mark);

        name.setText(mark.getName());
        first_mark.setText(String.valueOf(grades[0]));
        second_mark.setText(String.valueOf(grades[1]));
        third_mark.setText(String.valueOf(grades[2]));
        forth_mark.setText(String.valueOf(grades[3]));
        fifth_mark.setText(String.valueOf(grades[4]));
    }
}
