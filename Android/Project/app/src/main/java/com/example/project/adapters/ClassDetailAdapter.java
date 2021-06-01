package com.example.project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.models.ClassTraineeResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassDetailAdapter extends RecyclerView.Adapter<ClassDetailAdapter.ViewHolder>{
    private List<ClassTraineeResponse> classTraineeResponseList = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_detail, parent, false);
        return new ClassDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(classTraineeResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return classTraineeResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_trainee_number;
        public TextView tv_trainee_id;
        public TextView tv_trainee_name;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_trainee_number = itemView.findViewById(R.id.detail_number);
            tv_trainee_id = itemView.findViewById(R.id.detail_trainee_id);
            tv_trainee_name = itemView.findViewById(R.id.detail_trainee_name);

        }

        public void bind(ClassTraineeResponse classTraineeResponse) {
            tv_trainee_id.setText(classTraineeResponse.getTraineeUsername());
            tv_trainee_name.setText(classTraineeResponse.getTraineeName());
        }
    }

    public void setClassTraineeResponseList(List<ClassTraineeResponse> classTraineeResponseList) {
        this.classTraineeResponseList = classTraineeResponseList;
        notifyDataSetChanged();
    }

    public List<ClassTraineeResponse> getClassTraineeResponseList() {
        return classTraineeResponseList;
    }
}
