package com.example.project.ui.enrollment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.EnrollmentResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;


public class EnrollmentViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<EnrollmentResponse>> enrollmentResponseLiveData;

    public EnrollmentViewModel() {
        super();
        enrollmentResponseLiveData=appRepository.getEnrollmentResponseLiveData();

    }

    public LiveData<List<EnrollmentResponse>> getEnrollmentResponseLiveData() {
        return enrollmentResponseLiveData;
    }

    public void enrollments(String token,int classId){
        appRepository.enrollments(token,classId);
    }

}
