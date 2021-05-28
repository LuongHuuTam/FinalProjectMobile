package com.example.project.network.apis;

import com.example.project.models.LoginRequest;
import com.example.project.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("Users/authenticate")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
