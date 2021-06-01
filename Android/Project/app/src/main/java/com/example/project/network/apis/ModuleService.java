package com.example.project.network.apis;

import com.example.project.models.ModuleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ModuleService {
    @GET("Modules")
    Call<List<ModuleResponse>> getModules(@Header("Authorization") String token);
}
