package com.example.project.ui.enrollment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class EnrollmentViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public EnrollmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is enrollment fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
