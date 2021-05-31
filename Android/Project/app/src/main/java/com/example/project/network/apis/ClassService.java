package com.example.project.network.apis;

import android.graphics.drawable.shapes.OvalShape;

import com.example.project.models.ClassRequest;
import com.example.project.models.ClassResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClassService {
    @GET("Classes")
    Call<List<ClassResponse>> getClass(@Header("Authorization") String token);

    @GET("Classes/{role}class/{username}")
    Call<List<ClassResponse>> getTrainerTraineeCLass(@Header("Authorization") String token,@Path(value = "role",encoded = true) String role, @Path(value = "username", encoded = true) String username);

    @GET("Classes/{classId}")
    Call<ClassResponse> getClassInfo(@Header("Authorization")String token, @Path(value="classId",encoded = true) int classId);

    @POST("Classes")
    Call<Void> addClass(@Header("Authorization") String token, @Body ClassRequest classRequest);

    @DELETE("Classes/{classId}")
    Call<Void> deleteClass(@Header("Authorization")String token, @Path(value = "classId",encoded = true) int classId);


}

