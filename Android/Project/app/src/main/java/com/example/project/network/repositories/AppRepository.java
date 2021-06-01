package com.example.project.network.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.project.models.Assignment;
import com.example.project.models.ModuleRequest;
import com.example.project.models.class_models.ClassTraineeResponse;
import com.example.project.models.class_models.ClassRequest;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.models.DoFeedbackResponse;
import com.example.project.models.EnrollmentResponse;
import com.example.project.models.FeedbackResponse;
import com.example.project.models.LoginRequest;
import com.example.project.models.LoginResponse;
import com.example.project.models.ModuleResponse;
import com.example.project.models.QuestionResponse;
import com.example.project.network.apis.AssignmentService;
import com.example.project.network.apis.ClassService;
import com.example.project.network.apis.EnrollmentService;
import com.example.project.network.apis.FeedbackService;
import com.example.project.network.apis.LoginService;
import com.example.project.network.apis.ModuleService;
import com.example.project.network.apis.QuestionService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRepository {
    private static final String BASE_URL = "http://10.0.2.2:5000/api/";
    private LoginService loginService;
    private ClassService classService;
    private AssignmentService assignmentService;
    private EnrollmentService enrollmentService;
    private QuestionService questionService;
    private FeedbackService feedbackService;
    private ModuleService moduleService;

    private MutableLiveData<List<ModuleResponse>> moduleResponseLiveData;

    private MutableLiveData<String> addModuleResponseLiveData;
    private MutableLiveData<String> addModuleFailureLiveData;

    private MutableLiveData<String> deleteModuleResponseLiveData;
    private MutableLiveData<String> deleteModuleFailureLiveData;

    private MutableLiveData<ModuleResponse> getModuleInfoResponseLiveData;
    private MutableLiveData<String> getModuleInfoFailureLiveData;

    private MutableLiveData<String> updateModuleResponseLiveData;
    private MutableLiveData<String> updateModuleFailureLiveData;

    private MutableLiveData<List<QuestionResponse>> questionResponseLiveData;

    private MutableLiveData<List<Assignment>> assignmentResponseLiveData;

    private MutableLiveData<List<ClassResponse>> classResponseLiveData;
    private MutableLiveData<List<ClassResponse>> trainerTraineeClassResponseLiveData;

    private MutableLiveData<Void> addClassResponseLiveData;
    private MutableLiveData<String> addClassFailureLiveData;

    private MutableLiveData<Void> deleteClassResponseLiveData;
    private MutableLiveData<String> deleteClassFailureLiveData;

    private MutableLiveData<Void> updateClassResponseLiveData;
    private MutableLiveData<String> updateClassFailureLiveData;

    private MutableLiveData<ClassResponse> getClassInfoResponseLiveData;

    private MutableLiveData<List<ClassTraineeResponse>> getClassDetailLiveData;
    private MutableLiveData<String> getClassDetailFailureLiveData;

    private MutableLiveData<LoginResponse> loginResponseLiveData;
    private MutableLiveData<String> loginFailureLiveData;

    private MutableLiveData<List<EnrollmentResponse>> enrollmentResponseLiveData;
    private MutableLiveData<List<FeedbackResponse>> feedbackResponseLiveData;
    private MutableLiveData<List<DoFeedbackResponse>> doFeedbackResponseLiveData;


    public AppRepository() {
        loginResponseLiveData = new MutableLiveData<>();
        loginFailureLiveData = new MutableLiveData<>();

        classResponseLiveData = new MutableLiveData<>();
        trainerTraineeClassResponseLiveData = new MutableLiveData<>();
        addClassResponseLiveData = new MutableLiveData<>();
        addClassFailureLiveData = new MutableLiveData<>();
        deleteClassResponseLiveData = new MutableLiveData<>();
        deleteClassFailureLiveData = new MutableLiveData<>();
        updateClassResponseLiveData = new MutableLiveData<>();
        updateClassFailureLiveData = new MutableLiveData<>();
        getClassInfoResponseLiveData = new MutableLiveData<>();
        getClassDetailLiveData = new MutableLiveData<>();
        getClassDetailFailureLiveData = new MutableLiveData<>();

        assignmentResponseLiveData = new MutableLiveData<>();

        enrollmentResponseLiveData =new MutableLiveData<>();

        questionResponseLiveData =new MutableLiveData<>();

        feedbackResponseLiveData=new MutableLiveData<>();

        doFeedbackResponseLiveData=new MutableLiveData<>();

        moduleResponseLiveData=new MutableLiveData<>();
        addModuleResponseLiveData = new MutableLiveData<>();
        addModuleFailureLiveData = new MutableLiveData<>();
        deleteModuleResponseLiveData = new MutableLiveData<>();
        deleteModuleFailureLiveData = new MutableLiveData<>();
        getModuleInfoResponseLiveData = new MutableLiveData<>();
        getModuleInfoFailureLiveData = new MutableLiveData<>();
        updateModuleResponseLiveData = new MutableLiveData<>();
        updateModuleFailureLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        loginService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginService.class);

        classService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ClassService.class);

        assignmentService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AssignmentService.class);

        enrollmentService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EnrollmentService.class);

        questionService=new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuestionService.class);

        feedbackService=new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FeedbackService.class);

        moduleService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ModuleService.class);
    }

    //Login
    public void login(LoginRequest loginRequest){
        loginService.userLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginResponseLiveData.postValue(response.body());
                }
                else {
                    loginFailureLiveData.postValue("Login Failed !");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }
    public LiveData<LoginResponse> getLoginResponseLiveData() {
        return loginResponseLiveData;
    }
    public MutableLiveData<String> getLoginFailureLiveData(){
        return loginFailureLiveData;
    }

    //Get All Class
    public void classes(String token){
        classService.getClass("Bearer " + token).enqueue(new Callback<List<ClassResponse>>() {
            @Override
            public void onResponse(Call<List<ClassResponse>> call, Response<List<ClassResponse>> response) {
                if(response.body()!=null){
                    classResponseLiveData.postValue(response.body());
                    Log.i("CLASS RESPONSE LIVE DATA POST VALUE", "123"+classResponseLiveData.toString());
                }else{
                    classResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<ClassResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<ClassResponse>> getClassResponseLiveData() {
        return classResponseLiveData;
    }

    //Get Class Information
    public void getClassInfo(String token, int classId){
        classService.getClassInfo("Bearer " + token,classId ).enqueue(new Callback<ClassResponse>() {
            @Override
            public void onResponse(Call<ClassResponse> call, Response<ClassResponse> response) {
                if(response.body()!=null){
                    getClassInfoResponseLiveData.postValue(response.body());
                }else{
                    getClassInfoResponseLiveData.postValue(new ClassResponse());
                }
            }
            @Override
            public void onFailure(Call<ClassResponse> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<ClassResponse> getGetClassInfoResponseLiveData() {
        return getClassInfoResponseLiveData;
    }

    //Get Class Detail
    public void getClassDetail(String token, int classId){
        classService.getClassDetail("Bearer " + token,classId).enqueue(new Callback<List<ClassTraineeResponse>>() {
            @Override
            public void onResponse(Call<List<ClassTraineeResponse>> call, Response<List<ClassTraineeResponse>> response) {
                if(response.body()!=null){
                    getClassDetailLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ClassTraineeResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<ClassTraineeResponse>> getGetClassDetailLiveData() {
        return getClassDetailLiveData;
    }

    public MutableLiveData<String> getGetClassDetailFailureLiveData() {
        return getClassDetailFailureLiveData;
    }

    //Update class
    public void updateClass(String token, int classId, ClassRequest classRequest){
        classService.updateClass("Bearer " + token, classId,classRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    updateClassResponseLiveData.postValue(response.body());
                } else
                    updateClassFailureLiveData.postValue("Update fail");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                updateClassFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Void> getUpdateClassResponseLiveData() {
        return updateClassResponseLiveData;
    }

    public MutableLiveData<String> getUpdateFailureLiveData() {
        return updateClassFailureLiveData;
    }

    //Delete class
    public void deleteClass(String token, int classId){
        classService.deleteClass("Bearer " + token, classId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    deleteClassResponseLiveData.postValue(response.body());
                }
                else{
                    deleteClassFailureLiveData.postValue("Fail to delete");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                addClassFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Void> getDeleteClassResponseLiveData() {
        return deleteClassResponseLiveData;
    }

    public MutableLiveData<String> getDeleteClassFailureLiveData() {
        return deleteClassFailureLiveData;
    }

    //Add class
    public void addClasses(String token, ClassRequest classRequest){
        classService.addClass("Bearer " + token, classRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    addClassResponseLiveData.postValue(response.body());
                }
                else {
                    addClassFailureLiveData.postValue("Add class Error !");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                addClassFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Void> getAddClassResponseLiveData() {
        return addClassResponseLiveData;
    }
    public MutableLiveData<String> getAddClassFailureLiveData() {
        return addClassFailureLiveData;
    }

    //Get trainer and trainee class
    public void trainerTraineeClass(String token, String role, String username){
        classService.gettrainerTraineeClass("Bearer " + token, role, username).enqueue(new Callback<List<ClassResponse>>() {
            @Override
            public void onResponse(Call<List<ClassResponse>> call, Response<List<ClassResponse>> response) {
                if(response.body()!=null){
                    trainerTraineeClassResponseLiveData.postValue(response.body());
                }else{
                    trainerTraineeClassResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<ClassResponse>> call, Throwable t) {

            }
        });

    }
    public MutableLiveData<List<ClassResponse>> getTrainerClassResponseLiveData() {
        return trainerTraineeClassResponseLiveData;
    }

    //Get enrollment
    public void  enrollments(String token,int classId){
        enrollmentService.getEnrollment("Bearer "+token, classId).enqueue(new Callback<List<EnrollmentResponse>>() {
            @Override
            public void onResponse(Call<List<EnrollmentResponse>> call, Response<List<EnrollmentResponse>> response) {
                if (response.body() != null) {
                    enrollmentResponseLiveData.postValue(response.body());
                } else {
                    enrollmentResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<EnrollmentResponse>> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<List<EnrollmentResponse>> getEnrollmentResponseLiveData() {
        return enrollmentResponseLiveData;
    }



    //Get Assignment
    public void assignments(String token)
    {

        assignmentService.getAssignment("Bearer" + token).enqueue(new Callback<List<Assignment>>() {
            @Override
            public void onResponse(Call<List<Assignment>> call, Response<List<Assignment>> response) {
                if(response.body()!=null)
                {
                    assignmentResponseLiveData.postValue(response.body());
                }
                else{
                    assignmentResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Assignment>> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<List<Assignment>> getAssignmentLiveData(){
        return assignmentResponseLiveData;
    }

    //Get Question
    public void  questions(String token,int topicId){
        questionService.getQuestions(token,topicId).enqueue(new Callback<List<QuestionResponse>>() {
            @Override
            public void onResponse(Call<List<QuestionResponse>> call, Response<List<QuestionResponse>> response) {
                if(response.body()!=null){
                    questionResponseLiveData.postValue(response.body());
                }
                else {
                    questionResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<QuestionResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<QuestionResponse>> getQuestionResponseLiveData(){
        return questionResponseLiveData;
    }

    //Get Feedback
    public void feedbacks(String token){
        feedbackService.getFeedbacks(token).enqueue(new Callback<List<FeedbackResponse>>() {
            @Override
            public void onResponse(Call<List<FeedbackResponse>> call, Response<List<FeedbackResponse>> response) {
                if(response.body()!=null){
                    feedbackResponseLiveData.postValue(response.body());
                }
                else {
                    feedbackResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<FeedbackResponse>> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<List<FeedbackResponse>> getFeedbackResponseLiveData(){
        return feedbackResponseLiveData;
    }

    //Get do feedback
    public void doFeedbacks(String token,String traineeId){
        feedbackService.getFeedbacksTraineeDo(token,traineeId).enqueue(new Callback<List<DoFeedbackResponse>>() {
            @Override
            public void onResponse(Call<List<DoFeedbackResponse>> call, Response<List<DoFeedbackResponse>> response) {
                if(response.body()!=null){
                    doFeedbackResponseLiveData.postValue(response.body());
                }
                else {
                    doFeedbackResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<DoFeedbackResponse>> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<List<DoFeedbackResponse>> getDoFeedbackResponseLiveData(){
        return doFeedbackResponseLiveData;
    }

    //Get module
    public void modules(String token){
        moduleService.getModules(token).enqueue(new Callback<List<ModuleResponse>>() {
            @Override
            public void onResponse(Call<List<ModuleResponse>> call, Response<List<ModuleResponse>> response) {
                if(response.body()!=null){
                    moduleResponseLiveData.postValue(response.body());
                }
                else {
                    moduleResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<ModuleResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<ModuleResponse>> getModuleResponseLiveData(){
        return moduleResponseLiveData;
    }

    //Add module
    public void addModule(String token, ModuleRequest moduleRequest){
        moduleService.addModule(token,moduleRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    addModuleResponseLiveData.postValue(response.body());
                }else
                    addModuleFailureLiveData.postValue("Fail");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                addModuleFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<String> getAddModuleResponseLiveData() {
        return addModuleResponseLiveData;
    }

    public MutableLiveData<String> getAddModuleFailureLiveData() {
        return addModuleFailureLiveData;
    }

   public void deleteModule(String token, int moduleId){
        moduleService.deleteModule(token,moduleId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    deleteModuleResponseLiveData.postValue(response.body());
                }
                else
                    deleteModuleFailureLiveData.postValue("Fail to delete");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                deleteModuleFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
   }

    public MutableLiveData<String> getDeleteModuleResponseLiveData() {
        return deleteModuleResponseLiveData;
    }

    public MutableLiveData<String> getDeleteModuleFailureLiveData() {
        return deleteModuleFailureLiveData;
    }

    //Get Module information
    public void getModuleInfo(String token, int moduleId){
        moduleService.getModuleInfo(token, moduleId).enqueue(new Callback<ModuleResponse>() {
            @Override
            public void onResponse(Call<ModuleResponse> call, Response<ModuleResponse> response) {
                if(response.body()!=null){
                    getModuleInfoResponseLiveData.postValue(response.body());
                } else{
                    getModuleInfoFailureLiveData.postValue("Fail to get module information");
                }
            }
            @Override
            public void onFailure(Call<ModuleResponse> call, Throwable t) {
                getModuleInfoFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<ModuleResponse> getGetModuleInfoResponseLiveData() {
        return getModuleInfoResponseLiveData;
    }

    public MutableLiveData<String> getGetModuleInfoFailureLiveData() {
        return getModuleInfoFailureLiveData;
    }

    public void updateModule(String token, int moduleId, ModuleRequest moduleRequest){
        moduleService.updateModule(token,moduleId,moduleRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    updateModuleResponseLiveData.postValue(response.body());
                } else {
                    updateModuleFailureLiveData.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                updateModuleFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<String> getUpdateModuleResponseLiveData() {
        return updateModuleResponseLiveData;
    }

    public MutableLiveData<String> getUpdateModuleFailureLiveData() {
        return updateModuleFailureLiveData;
    }
}


