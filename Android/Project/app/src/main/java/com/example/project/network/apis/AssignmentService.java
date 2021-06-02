package com.example.project.network.apis;

import com.example.project.models.Assignment;
import com.example.project.models.AssignmentRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AssignmentService {
    @GET("Assignments")
    Call<List<Assignment>> getAssignment(@Header("Authorization") String authen);

    @POST("Assignments")
    Call<String> addAssignment(@Header("Authorization") String token, @Body AssignmentRequest assignmentRequest);

}
