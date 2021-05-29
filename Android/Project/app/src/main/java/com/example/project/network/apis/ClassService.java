package com.example.project.network.apis;

import com.example.project.models.ClassResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ClassService {
    @GET("Classes")
    Call<List<ClassResponse>> getClass(@Header("Authorization") String authen);
}
