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
import com.example.project.models.FeedbackResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>{
    private List<FeedbackResponse> feedbackResponseList = new ArrayList();
    private FeedbackListener feedbackListener;

    @NonNull
    @NotNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FeedbackViewHolder holder, int position) {
        holder.bind(feedbackResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return feedbackResponseList.size();
    }


    public class FeedbackViewHolder extends RecyclerView.ViewHolder {
        public TextView feedbackID;
        public TextView Title;
        public TextView adminId;
        public ImageButton buttonDetail;
        public ImageButton buttonEdit;
        public ImageButton buttonDelete;

        public FeedbackViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            feedbackID = itemView.findViewById(R.id.f_feedback_id);
            Title = itemView.findViewById(R.id.f_title);
            adminId = itemView.findViewById(R.id.f_admin_id);
            buttonDetail = itemView.findViewById(R.id.f_btn_detail);

            buttonEdit = itemView.findViewById(R.id.q_btn_edit);
            buttonDelete = itemView.findViewById(R.id.q_btn_delete);
        }

        @SuppressLint("SetTextI18n")
        public void bind(FeedbackResponse feedbackResponse) {
            feedbackID.setText(Integer.toString(feedbackResponse.getFeedbackID()));
            Title.setText(feedbackResponse.getTitle());
            adminId.setText(feedbackResponse.getAdminID());

            buttonDetail.setOnClickListener(view->{

            });

            buttonEdit.setOnClickListener(view -> {

            });

            buttonDelete.setOnClickListener(view -> {

            });
        }
    }

    public void setFeedbackResponseList(List<FeedbackResponse> feedbackResponseList) {
        this.feedbackResponseList = feedbackResponseList;
        notifyDataSetChanged();
    }

    public interface FeedbackListener{
        void onDelete(int id);
    }

    public void setFeedbackListener(FeedbackListener feedbackListener) {
        this.feedbackListener = feedbackListener;
    }
}
