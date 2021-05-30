package com.example.project.network.apis;

import com.example.project.models.EnrollmentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface EnrollmentService {
    @GET("Enrollments")
    Call<List<EnrollmentResponse>> getEnrollment(@Header("Authorization") String authen, @Query("classId") int classId);
}
