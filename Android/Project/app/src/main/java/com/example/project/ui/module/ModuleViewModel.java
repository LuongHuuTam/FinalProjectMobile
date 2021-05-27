package com.example.project.ui.module;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModuleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ModuleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is module fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
