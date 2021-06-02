package com.example.project.network.apis;

import com.example.project.models.Assignment;
import com.example.project.models.AssignmentRequest;
import com.example.project.models.class_models.ClassRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AssignmentService {
    @GET("Assignments")
    Call<List<Assignment>> getAssignment(@Header("Authorization") String authen);

    @POST("Assignments")
    Call<String> addAssignment(@Header("Authorization") String token, @Body AssignmentRequest assignmentRequest);

    @PUT("Assignments")
    Call<String> updateAssignment(@Header("Authorization")String token,  @Body AssignmentRequest assignmentRequest);

    @POST("Assignments/delete")
    Call<String> deleteAssignment(@Header("Authorization") String token, @Body AssignmentRequest assignmentRequest);
}
