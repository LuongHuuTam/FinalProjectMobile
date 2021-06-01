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
import com.example.project.models.DoFeedbackResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeTraineeAdapter extends RecyclerView.Adapter<HomeTraineeAdapter.HomeViewHolder>{
    private List<DoFeedbackResponse> feedbackResponseList = new ArrayList();
    private DoFeedbackListener doFeedbackListener;

    @NonNull
    @NotNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dofeedback, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeViewHolder holder, int position) {
        holder.bind(feedbackResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return feedbackResponseList.size();
    }


    public class HomeViewHolder extends RecyclerView.ViewHolder {
        public TextView feedbackTitle;
        public TextView classId;
        public TextView className;
        public TextView moduleId;
        public TextView moduleName;
        public TextView endTime;
        public TextView status;
        public ImageButton buttonExe;


        public HomeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            feedbackTitle = itemView.findViewById(R.id.fd_feedback_title);
            classId = itemView.findViewById(R.id.fd_class_id);
            className = itemView.findViewById(R.id.fd_class_name);
            moduleId = itemView.findViewById(R.id.fd_module_id);

            moduleName = itemView.findViewById(R.id.fd_module_name);
            endTime = itemView.findViewById(R.id.fd_time_end);
            status = itemView.findViewById(R.id.fd_status);
            buttonExe = itemView.findViewById(R.id.fd_btn_exe);
        }

        @SuppressLint("SetTextI18n")
        public void bind(DoFeedbackResponse feedbackResponse) {
            feedbackTitle.setText(feedbackResponse.getFeedbackTitle());
            classId.setText(Integer.toString(feedbackResponse.getClassID()));
            className.setText(feedbackResponse.getClassName());
            moduleId.setText(Integer.toString(feedbackResponse.getModuleId()));
            moduleName.setText(feedbackResponse.getModuleName());
            endTime.setText(feedbackResponse.getEndTime().substring(0,10));
            if (feedbackResponse.isStatus()) {
                status.setText("Complete");
                buttonExe.setVisibility(View.GONE);
            }
            else {
                status.setText("InComplete");
            }


        }
    }

    public void setDoFeedbackResponseList(List<DoFeedbackResponse> feedbackResponseList) {
        this.feedbackResponseList = feedbackResponseList;
        notifyDataSetChanged();
    }

    public interface DoFeedbackListener{
        void onDelete(int id);
    }

    public void setDoFeedbackListener(DoFeedbackListener feedbackListener) {
        this.doFeedbackListener = feedbackListener;
    }
}
