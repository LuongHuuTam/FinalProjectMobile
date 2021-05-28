package com.example.project.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.LoginRequest;
import com.example.project.models.LoginResponse;
import com.example.project.network.repositories.AppRepository;

public class LoginViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<LoginResponse> loginResponseLiveData;
    private LiveData<String> loginFailureLiveData;

    public LoginViewModel() {
        super();
        loginResponseLiveData = appRepository.getLoginResponseLiveData();
        loginFailureLiveData = appRepository.getLoginFailureLiveData();
    }

    public LiveData<LoginResponse> getLoginResponseLiveData() {
        return loginResponseLiveData;
    }
    public LiveData<String> getLoginFailureLiveData(){
        return loginFailureLiveData;
    }
    public void login(LoginRequest loginRequest){
        appRepository.login(loginRequest);
    }

}
