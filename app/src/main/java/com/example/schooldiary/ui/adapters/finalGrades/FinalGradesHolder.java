package com.example.schooldiary.ui.adapters.finalGrades;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldiary.databinding.ItemFinalGradeBinding;
import com.example.schooldiary.model.Marks;

import java.util.ArrayList;
import java.util.List;

public class FinalGradesHolder extends RecyclerView.ViewHolder {

    private View itemView;

    public FinalGradesHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bindData(Marks mark) {
        char[] grades = mark.getMarks().toCharArray();

        ItemFinalGradeBinding binding = ItemFinalGradeBinding.bind(itemView);

        TextView name = binding.subjectName;
        TextView first_mark = binding.firstMark;
        TextView second_mark = binding.secondMark;
        TextView third_mark = binding.thirdMark;
        TextView forth_mark = binding.forthMark;
        TextView fifth_mark = binding.fifthMark;

        List<TextView> views = new ArrayList<>();
        views.add(first_mark);
        views.add(second_mark);
        views.add(third_mark);
        views.add(forth_mark);
        views.add(fifth_mark);

        name.setText(mark.getName());

        for (int i = 0; i < 5; i++) {
            if (i < grades.length) {
                views.get(i).setText(String.valueOf(grades[i]));
            } else {
                views.get(i).setText(" ");
            }
        }
    }
}
