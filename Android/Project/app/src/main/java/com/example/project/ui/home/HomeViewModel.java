package com.example.project.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.DoFeedbackResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private AppRepository appRepository = new AppRepository();
    private LiveData<List<DoFeedbackResponse>> doFeedbackResponseLiveData;


    public HomeViewModel() {
        super();
        doFeedbackResponseLiveData=appRepository.getDoFeedbackResponseLiveData();

    }

    public LiveData<List<DoFeedbackResponse>> getDoFeedbackResponseLiveData() {
        return doFeedbackResponseLiveData;
    }

    public void feedbacks(String token,String traineeId){
        appRepository.doFeedbacks(token,traineeId);
    }
}