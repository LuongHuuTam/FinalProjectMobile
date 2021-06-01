package com.example.project.network.apis;

import com.example.project.models.class_models.ClassRequest;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.models.class_models.ClassTraineeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClassService {
    @GET("Classes")
    Call<List<ClassResponse>> getClass(@Header("Authorization") String token);

    @GET("Classes/{role}class/{username}")
    Call<List<ClassResponse>> gettrainerTraineeClass(@Header("Authorization") String token,@Path(value = "role",encoded = true) String role, @Path(value = "username", encoded = true) String username);

    @GET("Classes/{classId}")
    Call<ClassResponse> getClassInfo(@Header("Authorization")String token, @Path(value="classId",encoded = true) int classId);

    @GET("Classes/trainee/{classId}")
    Call<List<ClassTraineeResponse>> getClassDetail(@Header("Authorization") String token, @Path(value = "classId",encoded = true) int classId);

    @PUT("Classes/{classId}")
    Call<Void> updateClass(@Header("Authorization")String token, @Path(value="classId",encoded = true) int classId,@Body ClassRequest classRequest);

    @POST("Classes")
    Call<Void> addClass(@Header("Authorization") String token, @Body ClassRequest classRequest);

    @DELETE("Classes/{classId}")
    Call<Void> deleteClass(@Header("Authorization")String token, @Path(value = "classId",encoded = true) int classId);



}

