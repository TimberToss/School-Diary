package com.example.schooldiary.ui.adapters.diary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schooldiary.R
import com.example.schooldiary.model.Subject

class SubjectAdapter(private val subjects: List<Subject>, private val listener: SubjectHolder.SubjectClickListener)
    : RecyclerView.Adapter<SubjectHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_subject, parent, false)
        return SubjectHolder(view, listener)
    }

    override fun onBindViewHolder(holder: SubjectHolder, position: Int) {
        holder.bindData(subjects[position])
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

}
//public class SubjectAdapter extends RecyclerView.Adapter<SubjectHolder> {
//
//    private List<Subject> subjects;
//    private SubjectHolder.SubjectClickListener listener;
//
//    public SubjectAdapter(List<Subject> subjects, SubjectHolder.SubjectClickListener listener) {
//        this.subjects = subjects;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public SubjectHolder onCreateViewHolder(ViewGroup group, int i) {
//
//        View view = LayoutInflater.from(group.getContext())
//                .inflate(R.layout.item_subject, group, false);
//
//        return new SubjectHolder(view, listener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SubjectHolder holder, int position) {
//        holder.bindData(subjects.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return subjects.size();
//    }
//}
