package com.example.project.network.apis;

import com.example.project.models.QuestionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface QuestionService {
    @GET("Questions")
    Call<List<QuestionResponse>> getQuestions(@Header("Authorization") String token, @Query("topicId") int topicId);
}
