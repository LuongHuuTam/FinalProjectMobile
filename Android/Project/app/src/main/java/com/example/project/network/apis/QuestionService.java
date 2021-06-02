package com.example.project.network.apis;

import com.example.project.models.Questions;
import com.example.project.models.QuestionTopicResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuestionService {
    @GET("Questions")
    Call<List<Questions>> getQuestions(@Header("Authorization") String token, @Query("topicId") int topicId);

    @GET("Questions/topic")
    Call<List<QuestionTopicResponse>> getQuestionTopic(@Header("Authorization") String token);

    @GET("Questions/{questionId}")
    Call<Questions> getQuestionInfo(@Header("Authorization") String token, @Path(value = "questionId",encoded = true) int questionId);

    @POST("Questions")
    Call<String> addQuestion(@Header("Authorization") String token, @Body Questions questionRequest);

    @PUT("Questions/{questionId}")
    Call<String> updateQuestion(@Header("Authorization")String token, @Path(value = "questionId",encoded = true) int questionId , @Body Questions questionRequest);

    @DELETE("Questions/{questionId}")
    Call<String> deleteQuestion(@Header("Authorization")String token, @Path(value = "questionId",encoded = true) int questionId);
}
