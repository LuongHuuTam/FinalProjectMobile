package com.example.project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.models.Assignment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder>{

    private List<Assignment> assignmentList = new ArrayList();
    private AssignmentListener assignmentListener;

    @NonNull
    @NotNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AssignmentViewHolder holder, int position) {
        holder.bind(assignmentList.get(position));
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder{
        public TextView number;
        public TextView courseName;
        public TextView className;
        public TextView trainerName;
        public TextView registrationCode;
        public ImageButton buttonEdit;
        public ImageButton buttonDelete;
        public ImageButton buttonAdd;

        public AssignmentViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number_content);
            courseName = itemView.findViewById(R.id.course_name_content);
            className = itemView.findViewById(R.id.class_name_content);
            trainerName = itemView.findViewById(R.id.trainer_name_content);
            registrationCode = itemView.findViewById(R.id.register_code_content);

            buttonEdit = itemView.findViewById(R.id.btn_edit);
            buttonDelete = itemView.findViewById(R.id.btn_delete);
        }

        public void bind(Assignment assignment) {
            number.setText("1");
            className.setText(assignment.getClassName());
            courseName.setText(assignment.getModuleName());
            trainerName.setText(assignment.getTrainerName());
            registrationCode.setText(assignment.getRegistrationCode());

            buttonEdit.setOnClickListener(view -> {

            });
            buttonDelete.setOnClickListener(view -> {
                if(assignmentListener!=null){
                    assignmentListener.onDelete(assignment.getClassID(),
                            assignment.getModuleID(),
                            assignment.getTrainerID());
                }
            });
        }
    }
        public void setAssignmentList(List<Assignment> assignmentList) {
            this.assignmentList = assignmentList;

            notifyDataSetChanged();
        }
        public interface AssignmentListener{
            void onDelete(int classID, int moduleID, String trainerID);
        }

        public void setAssignmentListener(AssignmentAdapter.AssignmentListener assignmentListener) {
            this.assignmentListener = assignmentListener;
        }
}
