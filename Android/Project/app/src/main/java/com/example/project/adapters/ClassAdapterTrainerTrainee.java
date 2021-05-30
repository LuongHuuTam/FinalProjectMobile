package com.example.project.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.models.ClassResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapterTrainerTrainee extends RecyclerView.Adapter<ClassAdapterTrainerTrainee.ClassViewHolder> {
    private List<ClassResponse> trainerTraineeClassResponseList = new ArrayList();

    @NonNull
    @NotNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_trainer_trainee, parent, false);
        return new ClassViewHolder(view);
    }

    //set up information
    @Override
    public void onBindViewHolder(@NonNull @NotNull ClassViewHolder holder, int position) {
        holder.bind(trainerTraineeClassResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return trainerTraineeClassResponseList.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView classID;
        public TextView className;
        public TextView traineeCount;
        public ImageButton buttonDetail;

        public ClassViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            classID = itemView.findViewById(R.id.class_id);
            className = itemView.findViewById(R.id.class_name);
            traineeCount = itemView.findViewById(R.id.trainee_count);

            buttonDetail = itemView.findViewById(R.id.btn_detail);

        }

        @SuppressLint("SetTextI18n")
        public void bind(ClassResponse classResponse) {

            classID.setText(Integer.toString(classResponse.getClassID()));
            className.setText(classResponse.getName());
            traineeCount.setText(Integer.toString(classResponse.getEnrollmentsCount()));

            buttonDetail.setOnClickListener(view -> {

            });
            buttonDetail.setOnClickListener(view -> {
                //do something
            });
        }

    }

    public void setTrainerClassResponseList(List<ClassResponse> trainerClassResponseList) {
        this.trainerTraineeClassResponseList = trainerClassResponseList;
        notifyDataSetChanged();
    }

}
