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
import com.example.project.models.EnrollmentResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentAdapter extends RecyclerView.Adapter<EnrollmentAdapter.EnrollmentViewHolder> {
    private List<EnrollmentResponse> enrollmentResponsesList = new ArrayList();
    private EnrollmentListener enrollmentListener;

    @NonNull
    @NotNull
    @Override
    public EnrollmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enrollment, parent, false);
        return new EnrollmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EnrollmentViewHolder holder, int position) {
        holder.bind(enrollmentResponsesList.get(position));
    }

    @Override
    public int getItemCount() {
        return enrollmentResponsesList.size();
    }


    public class EnrollmentViewHolder extends RecyclerView.ViewHolder {
        public TextView classID;
        public TextView className;
        public TextView traineeID;
        public TextView traineeName;
        public ImageButton buttonDetail;
        public ImageButton buttonEdit;
        public ImageButton buttonDelete;

        public EnrollmentViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            classID = itemView.findViewById(R.id.e_class_id);
            className = itemView.findViewById(R.id.e_class_name);
            traineeID = itemView.findViewById(R.id.e_trainee_id);
            traineeName = itemView.findViewById(R.id.e_trainee_name);
            buttonDetail = itemView.findViewById(R.id.e_btn_detail);

            buttonEdit = itemView.findViewById(R.id.e_btn_edit);
            buttonDelete = itemView.findViewById(R.id.e_btn_delete);
        }

        @SuppressLint("SetTextI18n")
        public void bind(EnrollmentResponse enrollmentResponse) {
            classID.setText(Integer.toString(enrollmentResponse.getClassId()));
            className.setText(enrollmentResponse.getClassName());
            traineeID.setText(enrollmentResponse.getTraineeId());
            traineeName.setText(enrollmentResponse.getTraineeName());

            buttonDetail.setOnClickListener(view -> {

            });

            buttonEdit.setOnClickListener(view -> {

            });

            buttonDelete.setOnClickListener(view -> {
                if(enrollmentListener!=null){
                    //enrollmentListener.onDelete(classResponse.getClassID());
                }
            });
        }

    }


    public void setEnrollmentResponsesList(List<EnrollmentResponse> enrollmentResponsesList) {
        this.enrollmentResponsesList = enrollmentResponsesList;
        notifyDataSetChanged();
    }

    public interface EnrollmentListener{
        void onDelete(int id);
    }

    public void setEnrollmentListener(EnrollmentAdapter.EnrollmentListener enrollmentListener) {
        this.enrollmentListener = enrollmentListener;
    }
}
