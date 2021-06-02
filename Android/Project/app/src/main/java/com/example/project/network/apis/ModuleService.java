package com.example.project.network.apis;

import com.example.project.models.module_models.ModuleRequest;
import com.example.project.models.module_models.ModuleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ModuleService {
    @GET("Modules")
    Call<List<ModuleResponse>> getModules(@Header("Authorization") String token);

    @GET("Modules/{moduleId}")
    Call<ModuleResponse> getModuleInfo(@Header("Authorization") String token, @Path(value = "moduleId") int moduleId);

    @PUT("Modules/{moduleId}")
    Call<String> updateModule(@Header("Authorization")String token, @Path(value = "moduleId") int id, @Body ModuleRequest moduleRequest);

    @POST("Modules")
    Call<String> addModule(@Header("Authorization")String token, @Body ModuleRequest moduleRequest);

    @DELETE("Modules/{moduleId}")
    Call<String> deleteModule(@Header("Authorization")String token, @Path(value = "moduleId",encoded = true) int moduleId);

}
