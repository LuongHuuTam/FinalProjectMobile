package com.example.project.network.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.project.models.ClassResponse;
import com.example.project.models.LoginRequest;
import com.example.project.models.LoginResponse;
import com.example.project.network.apis.ClassService;
import com.example.project.network.apis.LoginService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRepository {
    private static final String BASE_URL = "http://10.0.2.2:5000/api/";
    private LoginService loginService;
    private ClassService classService;

    private MutableLiveData<List<ClassResponse>> classResponseLiveData;

    private MutableLiveData<LoginResponse> loginResponseLiveData;
    private MutableLiveData<String> loginFailureLiveData;

    public AppRepository() {
        loginResponseLiveData = new MutableLiveData<>();
        loginFailureLiveData = new MutableLiveData<>();
        classResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        loginService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginService.class);

        classService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ClassService.class);

    }

    public void login(LoginRequest loginRequest){
        loginService.userLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginResponseLiveData.postValue(response.body());
                }
                else {
                    loginFailureLiveData.postValue("Login Failed !");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }
    public LiveData<LoginResponse> getLoginResponseLiveData() {
        return loginResponseLiveData;
    }
    public MutableLiveData<String> getLoginFailureLiveData(){
        return loginFailureLiveData;
    }

    public void classes(String token){
        classService.getClass("Bearer " + token).enqueue(new Callback<List<ClassResponse>>() {
            @Override
            public void onResponse(Call<List<ClassResponse>> call, Response<List<ClassResponse>> response) {
                if(response.body()!=null){
                    classResponseLiveData.postValue(response.body());
                }else{
                    classResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<ClassResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<ClassResponse>> getClassResponseLiveData() {
        return classResponseLiveData;
    }
}

