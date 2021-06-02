package com.example.project.ui.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.TrainerResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class TrainerViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<TrainerResponse>> trainerResponseLiveData;

    public TrainerViewModel(){
        super();
        trainerResponseLiveData = appRepository.getTrainerResponseLiveData();
    }

    public LiveData<List<TrainerResponse>> getTrainerResponseLiveData(){
        return trainerResponseLiveData;
    }

    public void trainer(String token){
        appRepository.trainer(token);
    }
}
