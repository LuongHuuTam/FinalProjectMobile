package com.example.project.ui.classes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.class_models.ClassRequest;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.models.class_models.ClassTraineeResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class ClassViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<ClassResponse>> classResponseLiveData;
    private LiveData<List<ClassResponse>> trainerClassResponseLiveData;
    private LiveData<Void> addClassResponseLiveData;
    private LiveData<String> addClassFailureLiveData;
    private LiveData<ClassResponse> getClassInfoResponseLiveData;
    private LiveData<Void> deleteClassResponseLiveData;
    private LiveData<Void> updateClassResponseLiveData;
    private LiveData<List<ClassTraineeResponse>> getClassDetailResponseLiveData;
    private LiveData<String> getClassDetailFailureLiveData;
    public ClassViewModel() {
        super();
        classResponseLiveData = appRepository.getClassResponseLiveData();
        trainerClassResponseLiveData = appRepository.getTrainerClassResponseLiveData();
        addClassResponseLiveData = appRepository.getAddClassResponseLiveData();
        addClassFailureLiveData = appRepository.getAddClassFailureLiveData();
        getClassInfoResponseLiveData = appRepository.getGetClassInfoResponseLiveData();
        deleteClassResponseLiveData = appRepository.getDeleteClassResponseLiveData();
        updateClassResponseLiveData = appRepository.getUpdateClassResponseLiveData();
        getClassDetailResponseLiveData = appRepository.getGetClassDetailLiveData();
        getClassDetailFailureLiveData = appRepository.getGetClassDetailFailureLiveData();

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

    public LiveData<ClassResponse> getGetClassInfoResponseLiveData(){
        return getClassInfoResponseLiveData;
    }
    public LiveData<Void> getDeleteClassResponseLiveData(){
        return deleteClassResponseLiveData;
    }

    public LiveData<String> getAddClassFailureLiveData() {
        return addClassFailureLiveData;
    }

    public LiveData<Void> getUpdateClassResponseLiveData() {
        return updateClassResponseLiveData;
    }

    public LiveData<List<ClassTraineeResponse>> getGetClassDetailResponseLiveData() {
        return getClassDetailResponseLiveData;
    }

    public LiveData<String> getGetClassDetailFailureLiveData() {
        return getClassDetailFailureLiveData;
    }

    public void classes(String token){
        appRepository.classes(token);
    }

    public void trainerTraineeClass(String token,String role, String username){appRepository.trainerTraineeClass(token,role,username);}

    public void addClasses(String token, ClassRequest classRequest){appRepository.addClasses(token,classRequest);}

    public void getClassInfo(String token, int Id){appRepository.getClassInfo(token,Id);}

    public void deleteClass(String token, int Id){appRepository.deleteClass(token,Id);}

    public void updateClass(String token, int Id, ClassRequest classRequest){appRepository.updateClass(token,Id,classRequest);}

    public void getClassTraineeList(String token, int classId){appRepository.getClassDetail(token,classId);}

}