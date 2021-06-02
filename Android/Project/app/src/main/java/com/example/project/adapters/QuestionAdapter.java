package com.example.project.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.models.Questions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private List<Questions> questionResponseList = new ArrayList();
    private QuestionDelete questionDelete;
    private QuestionEdit questionEdit;


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
        AlertDialog.Builder alertDialogBuilder;

        public QuestionViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            topicID = itemView.findViewById(R.id.q_topic_id);
            topicName = itemView.findViewById(R.id.q_topic_name);
            questionID = itemView.findViewById(R.id.q_question_id);
            questionContent = itemView.findViewById(R.id.q_question_content);

            buttonEdit = itemView.findViewById(R.id.q_btn_edit);
            buttonDelete = itemView.findViewById(R.id.q_btn_delete);
            alertDialogBuilder = new AlertDialog.Builder(itemView.getContext());
        }

        @SuppressLint("SetTextI18n")
        public void bind(Questions questionResponse) {
            topicID.setText(Integer.toString(questionResponse.getTopicId()));
            topicName.setText(questionResponse.getTopicName());
            questionID.setText(Integer.toString(questionResponse.getQuestionID()));
            questionContent.setText(questionResponse.getContent());

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(questionDelete!=null){
                        alertDialogBuilder.setTitle("Delete");
                        alertDialogBuilder.setMessage("Are you sure you want to delete this question")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        questionDelete.onDelete(questionResponse.getQuestionID());
                                        Toast.makeText(itemView.getContext(), "Delete success", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }
            });

            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(questionEdit!=null){
                        questionEdit.onEdit(questionResponse.getQuestionID());
                    }
                }
            });
        }
    }

    public void setQuestionResponseList(List<Questions> questionResponseList) {
        this.questionResponseList = questionResponseList;
        notifyDataSetChanged();
    }

    public interface QuestionDelete{
        void onDelete(int id);
    }

    public void setQuestionDelete(QuestionDelete questionDelete) {
        this.questionDelete = questionDelete;
    }

    public interface QuestionEdit{
        void onEdit(int id);
    }

    public void setQuestionEdit(QuestionEdit questionEdit) {
        this.questionEdit = questionEdit;
    }
}
