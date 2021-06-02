package com.example.project.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.models.module_models.ModuleResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ModuleAdapterTraineeTrainer extends RecyclerView.Adapter<ModuleAdapterTraineeTrainer.ModuleViewHolder>{
    private List<ModuleResponse> moduleResponseList = new ArrayList();

    @NonNull
    @NotNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_module, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ModuleViewHolder holder, int position) {
        holder.bind(moduleResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return moduleResponseList.size();
    }

    public class ModuleViewHolder extends RecyclerView.ViewHolder {
        public TextView moduleId;
        public TextView moduleName;
        public TextView adminID;
        public TextView startDate;
        public TextView endDate;
        public TextView feedbackTitle;
        public TextView feedbackStartTime;
        public TextView feedbackEndTime;

        public ImageButton buttonEdit;
        public ImageButton buttonDelete;
        AlertDialog.Builder alertDialogBuilder;

        public ModuleViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            moduleId = itemView.findViewById(R.id.m_module_id);
            moduleName = itemView.findViewById(R.id.m_module_name);
            adminID = itemView.findViewById(R.id.m_admin_id);
            startDate = itemView.findViewById(R.id.m_startday);
            endDate = itemView.findViewById(R.id.m_enddate);
            feedbackTitle = itemView.findViewById(R.id.m_feedback_title);
            feedbackStartTime = itemView.findViewById(R.id.m_fb_startdate);
            feedbackEndTime = itemView.findViewById(R.id.m_fb_enddate);

            buttonEdit = itemView.findViewById(R.id.m_btn_edit);
            buttonDelete = itemView.findViewById(R.id.m_btn_delete);
            alertDialogBuilder = new AlertDialog.Builder(itemView.getContext());
        }

        @SuppressLint("SetTextI18n")
        public void bind(ModuleResponse moduleResponse) {
            moduleId.setText(Integer.toString(moduleResponse.getModuleID()));
            moduleName.setText(moduleResponse.getName());
            adminID.setText(moduleResponse.getAdminID());
            startDate.setText(moduleResponse.getStartTime().substring(0,10));
            endDate.setText(moduleResponse.getEndTime().substring(0,10));
            feedbackTitle.setText(moduleResponse.getFeedbackTitle());
            feedbackStartTime.setText(moduleResponse.getFeedbackStartTime().substring(0,16));
            feedbackEndTime.setText(moduleResponse.getFeedbackEndTime().substring(0,16));


            buttonEdit.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.GONE);


        }
    }


    public void setModuleResponseList(List<ModuleResponse> moduleResponseList) {
        this.moduleResponseList = moduleResponseList;
        notifyDataSetChanged();
    }
}
