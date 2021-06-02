package com.example.project.network.apis;

import com.example.project.models.TrainerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserService {
    @GET("Users/trainer")
    Call<List<TrainerResponse>> getTrainer(@Header("Authorization") String token);
}
