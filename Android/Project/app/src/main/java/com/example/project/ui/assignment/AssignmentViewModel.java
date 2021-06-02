package com.example.project.ui.assignment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.Assignment;
import com.example.project.models.AssignmentRequest;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class AssignmentViewModel extends ViewModel {

    private AppRepository appRepository = new AppRepository();
    private LiveData<List<Assignment>> assignmentLiveData;

    private  LiveData<String> addAssignmentResponseLiveData;
    private  LiveData<String> addAssignmentFailureLiveData;

    private LiveData<String> deleteAssignmentResponseLiveData;
    private LiveData<String> deleteAssignmentFailureLiveData;

    private LiveData<String> updateAssignmentResponseLiveData;
    private LiveData<String> updateAssignmentFailureLiveData;

    public AssignmentViewModel() {
        super();
        assignmentLiveData = appRepository.getAssignmentLiveData();

        addAssignmentFailureLiveData = appRepository.getAddAssignmentResponseLiveData();
        addAssignmentFailureLiveData = appRepository.getAddAssignmentFailureLiveData();

        updateAssignmentResponseLiveData = appRepository.getUpdateAssignmentResponseLiveData();
        updateAssignmentFailureLiveData = appRepository.getUpdateAssignmentFailureLiveData();

        deleteAssignmentResponseLiveData = appRepository.getDeleteAssignmentResponseLiveData();
        deleteAssignmentFailureLiveData = appRepository.getDeleteAssignmentFailureLiveData();
    }

    public LiveData<String> getDeleteAssignmentResponseLiveData() {
        return deleteAssignmentResponseLiveData;
    }

    public LiveData<String> getDeleteAssignmentFailureLiveData() {
        return deleteAssignmentFailureLiveData;
    }

    public LiveData<String> getUpdateAssignmentResponseLiveData() {
        return updateAssignmentResponseLiveData;
    }

    public LiveData<String> getUpdateAssignmentFailureLiveData() {
        return updateAssignmentFailureLiveData;
    }

    public LiveData<List<Assignment>> getAssignmentLiveData() {
        return assignmentLiveData;
    }

    public LiveData<String> getAddAssignmentResponseLiveData(){
        return addAssignmentResponseLiveData;
    }
    public LiveData<String> getAddAssignmentFailureLiveData(){
        return addAssignmentFailureLiveData;
    }

    public void assignments(String token){
        appRepository.assignments(token);
    }
    public void addAssignment(String token, AssignmentRequest assignmentRequest){
        appRepository.addAssignment(token, assignmentRequest);
    }

    public void updateAssignment(String token, AssignmentRequest assignmentRequest){
        appRepository.updateAssignment(token, assignmentRequest);
    }

    public void deleteAssignment(String token, AssignmentRequest assignmentRequest){
        appRepository.deleteAssignment(token, assignmentRequest);
    }

}