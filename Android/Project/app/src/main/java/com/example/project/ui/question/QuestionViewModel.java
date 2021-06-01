package com.example.project.ui.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.QuestionResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class QuestionViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<QuestionResponse>> questionResponseLiveData;


    public QuestionViewModel() {
        super();
        questionResponseLiveData=appRepository.getQuestionResponseLiveData();

    }

    public LiveData<List<QuestionResponse>> getQuestionResponseLiveData() {
        return questionResponseLiveData;
    }

    public void questions(String token,int topicId){
        appRepository.questions(token,topicId);
    }
}
