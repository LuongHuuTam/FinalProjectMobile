package com.example.project.ui.module;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.module_models.ModuleRequest;
import com.example.project.models.module_models.ModuleResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class ModuleViewModel extends ViewModel {

    private AppRepository appRepository = new AppRepository();
    private LiveData<List<ModuleResponse>> moduleResponseLiveData;

    private LiveData<String> addModuleResponseLiveData;
    private LiveData<String> addModuleFailureLiveData;

    private LiveData<String> deleteModuleResponseLiveData;
    private LiveData<String> deleteModuleFailureLiveData;

    private LiveData<ModuleResponse> getModuleInfoResponseLiveData;
    private LiveData<String> getModuleInfoFailureLiveData;

    private LiveData<String> updateModuleResponseLiveData;
    private LiveData<String> updateModuleFailureLiveData;

    private LiveData<List<ModuleResponse>> moduleTraineeTrainerResponseLiveData;

    public ModuleViewModel() {
        super();
        moduleResponseLiveData = appRepository.getModuleResponseLiveData();

        addModuleResponseLiveData = appRepository.getAddModuleResponseLiveData();
        addModuleFailureLiveData = appRepository.getAddModuleFailureLiveData();

        deleteModuleResponseLiveData = appRepository.getDeleteModuleResponseLiveData();
        deleteModuleFailureLiveData = appRepository.getDeleteModuleFailureLiveData();

        getModuleInfoResponseLiveData = appRepository.getGetModuleInfoResponseLiveData();
        getModuleInfoFailureLiveData = appRepository.getGetModuleInfoFailureLiveData();

        updateModuleResponseLiveData = appRepository.getUpdateModuleResponseLiveData();
        updateModuleFailureLiveData = appRepository.getUpdateModuleFailureLiveData();
        moduleTraineeTrainerResponseLiveData=appRepository.getModuleTraineeTrainerResponseLiveData();
    }

    public LiveData<List<ModuleResponse>> getModuleResponseLiveData() {
        return moduleResponseLiveData;
    }

    public LiveData<String> getAddModuleResponseLiveData() {
        return addModuleResponseLiveData;
    }

    public LiveData<String> getAddModuleFailureLiveData() {
        return addModuleFailureLiveData;
    }

    public LiveData<String> getDeleteModuleResponseLiveData() {
        return deleteModuleResponseLiveData;
    }

    public LiveData<String> getDeleteModuleFailureLiveData() {
        return deleteModuleFailureLiveData;
    }

    public LiveData<ModuleResponse> getGetModuleInfoResponseLiveData() {
        return getModuleInfoResponseLiveData;
    }

    public LiveData<String> getGetModuleInfoFailureLiveData() {
        return getModuleInfoFailureLiveData;
    }

    public LiveData<String> getUpdateModuleResponseLiveData() {
        return updateModuleResponseLiveData;
    }

    public LiveData<List<ModuleResponse>> getModuleTraineeTrainerResponseLiveData() {
        return moduleTraineeTrainerResponseLiveData;
    }

    public LiveData<String> getUpdateModuleFailureLiveData() {
        return updateModuleFailureLiveData;
    }

    public void modules(String token) {
        appRepository.modules(token);
    }

    public void addModule(String token, ModuleRequest moduleRequest) {
        appRepository.addModule(token, moduleRequest);
    }

    public void deleteModule(String token, int moduleId) {
        appRepository.deleteModule(token, moduleId);
    }

    public void getModuleInfo(String token, int moduleId){
        appRepository.getModuleInfo(token,moduleId);
    }

    public void updateModule(String token, int moduleId, ModuleRequest moduleRequest){
        appRepository.updateModule(token,moduleId,moduleRequest);
    }
    public void getModuleTraineeTrainer(String token, String role,String username){
        appRepository.moduleTraineeTrainer(token,role,username);
    }
}
