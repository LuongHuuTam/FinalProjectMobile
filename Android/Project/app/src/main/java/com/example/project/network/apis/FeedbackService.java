package com.example.project.network.apis;

import com.example.project.models.DoFeedbackResponse;
import com.example.project.models.FeedbackResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface FeedbackService {
    @GET("Feedbacks")
    Call<List<FeedbackResponse>> getFeedbacks(@Header("Authorization") String token);

    @GET("Feedbacks/dofeedback/{traineeId}")
    Call<List<DoFeedbackResponse>> getFeedbacksTraineeDo(@Header("Authorization") String token, @Path(value="traineeId",encoded = true) String traineeId);
}
