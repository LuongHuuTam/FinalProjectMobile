package com.example.project.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("Users/authenticate")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
