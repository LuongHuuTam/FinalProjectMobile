package com.example.project.ui.module;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.ModuleResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class ModuleViewModel extends ViewModel {

    private AppRepository appRepository = new AppRepository();
    private LiveData<List<ModuleResponse>> moduleResponseLiveData;


    public ModuleViewModel() {
        super();
        moduleResponseLiveData=appRepository.getModuleResponseLiveData();

    }

    public LiveData<List<ModuleResponse>> getModuleResponseLiveData() {
        return moduleResponseLiveData;
    }

    public void modules(String token){
        appRepository.modules(token);
    }
}
