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
import com.example.project.models.QuestionResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private List<QuestionResponse> questionResponseList = new ArrayList();
    private QuestionListener questionListener;

    @NonNull
    @NotNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull QuestionViewHolder holder, int position) {
        holder.bind(questionResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return questionResponseList.size();
    }


    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        public TextView topicID;
        public TextView topicName;
        public TextView questionID;
        public TextView questionContent;
        public ImageButton buttonEdit;
        public ImageButton buttonDelete;

        public QuestionViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            topicID = itemView.findViewById(R.id.q_topic_id);
            topicName = itemView.findViewById(R.id.q_topic_name);
            questionID = itemView.findViewById(R.id.q_question_id);
            questionContent = itemView.findViewById(R.id.q_question_content);

            buttonEdit = itemView.findViewById(R.id.q_btn_edit);
            buttonDelete = itemView.findViewById(R.id.q_btn_delete);
        }

        @SuppressLint("SetTextI18n")
        public void bind(QuestionResponse questionResponse) {
            topicID.setText(Integer.toString(questionResponse.getTopicId()));
            topicName.setText(questionResponse.getTopicName());
            questionID.setText(Integer.toString(questionResponse.getQuestionID()));
            questionContent.setText(questionResponse.getContent());

        }

    }

    public void setQuestionResponseList(List<QuestionResponse> questionResponseList) {
        this.questionResponseList = questionResponseList;
        notifyDataSetChanged();
    }

    public interface QuestionListener{
        void onDelete(int id);
    }

    public void setQuestionListener(QuestionAdapter.QuestionListener questionListener) {
        this.questionListener = questionListener;
    }
}
