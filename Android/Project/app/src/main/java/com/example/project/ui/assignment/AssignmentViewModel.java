package com.example.project.ui.assignment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.Assignment;
import com.example.project.models.ClassResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class AssignmentViewModel extends ViewModel {

    private AppRepository appRepository = new AppRepository();
    private LiveData<List<Assignment>> assignmentLiveData;

    public AssignmentViewModel() {
        super();
        assignmentLiveData = appRepository.getAssignmentLiveData();
    }

    public LiveData<List<Assignment>> getAssignmentLiveData() {
        return assignmentLiveData;
    }

    public void assignments(String token){
        appRepository.assignments(token);
    }
}