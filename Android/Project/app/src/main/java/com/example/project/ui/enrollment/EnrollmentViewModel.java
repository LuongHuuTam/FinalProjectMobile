package com.example.project.ui.enrollment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.ClassResponse;
import com.example.project.models.EnrollmentResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;


public class EnrollmentViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<EnrollmentResponse>> enrollmentResponseLiveData;
    private LiveData<List<ClassResponse>> classResponseLiveData;

    public EnrollmentViewModel() {
        super();
        enrollmentResponseLiveData=appRepository.getEnrollmentResponseLiveData();
        classResponseLiveData=appRepository.getClassResponseLiveData();
    }

    public LiveData<List<EnrollmentResponse>> getEnrollmentResponseLiveData() {
        return enrollmentResponseLiveData;
    }

    public LiveData<List<ClassResponse>> getClassResponseLiveData() {
        return classResponseLiveData;
    }

    public void enrollments(String token,int classId){
        appRepository.enrollments(token,classId);
    }

    public void classes(String token){
        appRepository.classes(token);
    }
}
