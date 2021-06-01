package com.example.project.ui.feedback;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.FeedbackResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class FeedBackViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<FeedbackResponse>> feedbackResponseLiveData;


    public FeedBackViewModel() {
        super();
        feedbackResponseLiveData=appRepository.getFeedbackResponseLiveData();

    }

    public LiveData<List<FeedbackResponse>> getFeedbackResponseLiveData() {
        return feedbackResponseLiveData;
    }

    public void feedbacks(String token){
        appRepository.feedbacks(token);
    }
}
