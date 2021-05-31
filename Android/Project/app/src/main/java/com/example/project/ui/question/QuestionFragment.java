package com.example.project.ui.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.adapters.QuestionAdapter;
import com.example.project.models.QuestionResponse;
import com.example.project.sharepreference.SharedPreferencesManager;

import java.util.List;

public class QuestionFragment extends Fragment {
    private QuestionViewModel questionViewModel;
    private QuestionAdapter questionAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        questionViewModel =
                new ViewModelProvider(this).get(QuestionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_question, container, false);

        String token = "";
        int topicId=0;

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        questionViewModel.questions(token, topicId);

        questionAdapter = new QuestionAdapter();
        questionAdapter.setQuestionListener(new QuestionAdapter.QuestionListener() {
            @Override
            public void onDelete(int id) {

            }
        });
        RecyclerView recyclerView = root.findViewById(R.id.rv_question);
        recyclerView.setAdapter(questionAdapter);


        questionViewModel.getQuestionResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<QuestionResponse>>) questionResponseList -> {
            questionAdapter.setQuestionResponseList(questionResponseList);
        });
        return root;
    }
}
