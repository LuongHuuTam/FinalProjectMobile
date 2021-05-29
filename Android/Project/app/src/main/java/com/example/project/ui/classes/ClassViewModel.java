package com.example.project.ui.classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.ClassResponse;
import com.example.project.models.LoginRequest;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class ClassViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<ClassResponse>> classResponseLiveData;

    public ClassViewModel() {
        super();
        classResponseLiveData = appRepository.getClassResponseLiveData();
    }

    public LiveData<List<ClassResponse>> getClassResponseLiveData() {
        return classResponseLiveData;
    }

    public void classes(String token){
        appRepository.classes(token);
    }
}