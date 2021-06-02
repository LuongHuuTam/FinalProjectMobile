package com.example.project.ui.question;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.project.R;
import com.example.project.models.QuestionTopicResponse;
import com.example.project.models.Questions;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.models.module_models.ModuleResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.example.project.ui.classes.ClassViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionFragment extends Fragment {
    QuestionViewModel questionViewModel;
    View root;
    AutoCompleteTextView topic_name_selector;
    TextInputEditText ed_question_content;
    Button btnSave;
    TextInputLayout layout_question_content;
    String token;
    Questions questionResponse;
    List<String> topicOptions = new ArrayList<>();
    String[] items;
    int topicId = 0;
    int questionId = 0;
    AlertDialog.Builder alertDialogBuilder;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_question_add_question,container,false);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        topic_name_selector = root.findViewById(R.id.q_topic_name_selector);
        ed_question_content = root.findViewById(R.id.q_input_content);
        btnSave = root.findViewById(R.id.btn_save);
        layout_question_content = root.findViewById(R.id.outlinedTextQuestionContent);

        alertDialogBuilder = new AlertDialog.Builder(requireContext());

        if(SharedPreferencesManager.getLoginResponseValue(requireContext())!=null){
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
            questionViewModel.getQuestionTopic(token);
        }

        ed_question_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(ed_question_content.getText().toString().equals("")){
                    layout_question_content.setError("Please enter the question");
                }
                else{
                    layout_question_content.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            Gson gson = new Gson();
            String moduleData = bundle.getString("QUESTION_DATA", "");
            Type token = new TypeToken<Questions>() {
            }.getType();
             questionResponse = gson.fromJson(moduleData, token);
             if(questionResponse!=null){
                 ed_question_content.setText(questionResponse.getContent());
                 topic_name_selector.setText(questionResponse.getTopicName());
                 topic_name_selector.setEnabled(false);
                 questionId = questionResponse.getQuestionID();
                 topicId = questionResponse.getTopicId();
             }

            questionViewModel.getUpdateQuestionResponseLiveData().observe(getViewLifecycleOwner(), s -> {
                alertDialogBuilder.setTitle("Success");
                alertDialogBuilder.setMessage("Update question successful")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Navigation.findNavController(root).navigate(R.id.nav_question);
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
                Toast.makeText(getContext(), "Update question successful !", Toast.LENGTH_LONG).show();
            });

            btnSave.setOnClickListener(view -> {
                if(TextUtils.isEmpty(ed_question_content.getText().toString())){
                    alertDialogBuilder.setTitle("Error");
                    alertDialogBuilder.setMessage("Please enter question")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.show();
                }
                else{
                    updateQuestion();
                }
            });
        }

        else {
            questionViewModel.getAddQuestionResponseLiveData().observe(getViewLifecycleOwner(), s -> {
                alertDialogBuilder.setTitle("Success");
                alertDialogBuilder.setMessage("Update question successful")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Navigation.findNavController(root).navigate(R.id.nav_question);
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
                Toast.makeText(getContext(), "Update question successful !", Toast.LENGTH_LONG).show();
            });
            questionViewModel.getQuestionTopicResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<QuestionTopicResponse>>() {
                @Override
                public void onChanged(List<QuestionTopicResponse> questionTopicResponses) {
                    for(int i = 0; i<questionTopicResponses.size();i++){
                        topicOptions.add(Integer.toString(questionTopicResponses.get(i).getTopicId()) + " " + questionTopicResponses.get(i).getName());
                    }
                    items = new String[topicOptions.size()];
                    items = topicOptions.toArray(items);
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.list_item, items);
                    topic_name_selector.setText(arrayAdapter.getItem(0).toString(), false);
                    topic_name_selector.setAdapter(arrayAdapter);
                }
            });

            btnSave.setOnClickListener(view -> {
                if (TextUtils.isEmpty(ed_question_content.getText().toString())) {
                    alertDialogBuilder.setTitle("Error");
                    alertDialogBuilder.setMessage("Please enter question")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    alertDialog.show();
                } else {
                    addQuestion();
                }
            });
        }
        return root;
    }
    public void addQuestion(){
        Questions questionRequest = new Questions();
        questionRequest.setContent(ed_question_content.getText().toString());
        questionRequest.setTopicId(Integer.parseInt(topic_name_selector.getText().toString().substring(0,1)));
        if(token !=null){
            questionViewModel.addQuestion(token,questionRequest);
        }
    }
    public void updateQuestion(){
        Questions questionRequest = new Questions();
        questionRequest.setContent(ed_question_content.getText().toString());
        questionRequest.setTopicId(topicId);
        questionRequest.setQuestionID(questionId);
        if(token !=null){
            questionViewModel.updateQuestion(token,topicId,questionRequest);
        }
    }

}
