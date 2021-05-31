package com.example.project.ui.classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.ClassRequest;
import com.example.project.models.ClassResponse;
import com.example.project.models.LoginRequest;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class ClassViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<ClassResponse>> classResponseLiveData;
    private LiveData<List<ClassResponse>> trainerClassResponseLiveData;
    private LiveData<Void> addClassResponseLiveData;
    private LiveData<String> addClassFailureLiveData;

    public ClassViewModel() {
        super();
        classResponseLiveData = appRepository.getClassResponseLiveData();
        trainerClassResponseLiveData = appRepository.getTrainerClassResponseLiveData();
        addClassResponseLiveData = appRepository.getAddClassResponseLiveData();
        addClassFailureLiveData = appRepository.getAddClassFailureLiveData();
    }

    public LiveData<List<ClassResponse>> getClassResponseLiveData() {
        return classResponseLiveData;
    }

    public LiveData<List<ClassResponse>> getTrainerClassResponseLiveData() {
        return trainerClassResponseLiveData;
    }

    public LiveData<Void> getAddClassResponseLiveData() {
        return addClassResponseLiveData;
    }

    public LiveData<String> getAddClassFailureLiveData() {
        return addClassFailureLiveData;
    }

    public void classes(String token){
        appRepository.classes(token);
    }

    public void trainertraineeclass(String token,String role, String username){appRepository.trainertraineeclass(token,role,username);}

    public void addClasses(String token, ClassRequest classRequest){appRepository.addClasses(token,classRequest);}

}