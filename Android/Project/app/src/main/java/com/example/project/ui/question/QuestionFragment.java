package com.example.project.ui.question;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.adapters.QuestionAdapter;
import com.example.project.models.QuestionTopicResponse;
import com.example.project.models.Questions;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionFragment extends Fragment {
    private QuestionViewModel questionViewModel;
    private QuestionAdapter questionAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        questionViewModel =
                new ViewModelProvider(this).get(QuestionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_question, container, false);
        AutoCompleteTextView topic_selector;
        String token = "";
        ImageButton buttonAdd = root.findViewById(R.id.q_btn_add);
        topic_selector = root.findViewById(R.id.e_topic_selector);

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        questionViewModel.getQuestionTopicResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<QuestionTopicResponse>>() {
            @Override
            public void onChanged(List<QuestionTopicResponse> questionTopicResponses) {
                List<String> topicOptions = new ArrayList<>();
                topicOptions.add("0.All");
                for (int i = 0; i < questionTopicResponses.size(); i++) {
                    topicOptions.add(questionTopicResponses.get(i).getTopicId() + "." + questionTopicResponses.get(i).getName());
                }
                String[] items;
                items = new String[topicOptions.size()];
                items = topicOptions.toArray(items);
                ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.list_item, items);
                topic_selector.setText(arrayAdapter.getItem(0).toString(), false);
                topic_selector.setAdapter(arrayAdapter);
            }
        });

        if (token != null) {
            String finalToken1 = token;
            topic_selector.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int topicId = Integer.parseInt(topic_selector.getText().toString().substring(0, 1));
                    questionViewModel.questions(finalToken1, topicId);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            questionViewModel.getQuestionTopic(token);
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_question_to_question_addQuestion);
            }
        });

        questionAdapter = new QuestionAdapter();
        String finalToken = token;

        questionAdapter.setQuestionDelete(new QuestionAdapter.QuestionDelete() {
            @Override
            public void onDelete(int id) {
                questionViewModel.deleteQuestion(finalToken, id);
            }
        });

        questionAdapter.setQuestionEdit(new QuestionAdapter.QuestionEdit() {
            @Override
            public void onEdit(int id) {
                questionViewModel.getQuestionInfoResponseLiveData().observe(getViewLifecycleOwner(), new Observer<Questions>() {
                    @Override
                    public void onChanged(Questions questions) {
                        Gson gson = new Gson();
                        String questionResponseData = gson.toJson(questions);
                        Bundle bundle = new Bundle();
                        bundle.putString("QUESTION_DATA", questionResponseData);
                        Navigation.findNavController(root).navigate(R.id.action_nav_question_to_question_addQuestion, bundle);
                    }
                });
                if (finalToken != null) {
                    questionViewModel.getQuestionInfo(finalToken, id);
                }
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.rv_question);
        recyclerView.setAdapter(questionAdapter);


        questionViewModel.getQuestionResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<Questions>>) questionResponseList -> {
            questionAdapter.setQuestionResponseList(questionResponseList);
        });
        return root;
    }
}
