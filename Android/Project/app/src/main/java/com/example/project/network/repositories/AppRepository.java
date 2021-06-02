package com.example.project.network.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.project.models.Assignment;
import com.example.project.models.TrainerResponse;
import com.example.project.models.AssignmentRequest;
import com.example.project.models.QuestionTopicResponse;
import com.example.project.models.module_models.ModuleRequest;
import com.example.project.models.class_models.ClassTraineeResponse;
import com.example.project.models.class_models.ClassRequest;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.models.DoFeedbackResponse;
import com.example.project.models.EnrollmentResponse;
import com.example.project.models.FeedbackResponse;
import com.example.project.models.LoginRequest;
import com.example.project.models.LoginResponse;
import com.example.project.models.module_models.ModuleResponse;
import com.example.project.models.Questions;
import com.example.project.network.apis.AssignmentService;
import com.example.project.network.apis.ClassService;
import com.example.project.network.apis.EnrollmentService;
import com.example.project.network.apis.FeedbackService;
import com.example.project.network.apis.LoginService;
import com.example.project.network.apis.ModuleService;
import com.example.project.network.apis.QuestionService;
import com.example.project.network.apis.UserService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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
    private UserService userService;


    //region Module
    private MutableLiveData<List<ModuleResponse>> moduleResponseLiveData;

    private MutableLiveData<String> addModuleResponseLiveData;
    private MutableLiveData<String> addModuleFailureLiveData;

    private MutableLiveData<String> deleteModuleResponseLiveData;
    private MutableLiveData<String> deleteModuleFailureLiveData;

    private MutableLiveData<ModuleResponse> getModuleInfoResponseLiveData;
    private MutableLiveData<String> getModuleInfoFailureLiveData;

    private MutableLiveData<String> updateModuleResponseLiveData;
    private MutableLiveData<String> updateModuleFailureLiveData;
    //endregion

    //region Question
    private MutableLiveData<List<Questions>> questionResponseLiveData;

    private MutableLiveData<List<QuestionTopicResponse>> getQuestionTopicResponseLiveData;
    private MutableLiveData<String> getQuestionTopicFailureLiveData;

    private MutableLiveData<Questions> getQuestionInfoResponseLiveData;
    private MutableLiveData<String> getQuestionInfoFailureLiveData;

    private MutableLiveData<String> addQuestionResponseLiveData;
    private MutableLiveData<String> addQuestionFailureLiveData;

    private MutableLiveData<String> updateQuestionResponseLiveData;
    private MutableLiveData<String> updateQuestionFailureLiveData;

    private MutableLiveData<String> deleteQuestionResponseLiveData;
    private MutableLiveData<String> deleteQuestionFailureLiveData;
    //endregion

    private MutableLiveData<List<ModuleResponse>> moduleTraineeTrainerLiveData;


    private MutableLiveData<String> addAssignmentResponseLiveData;
    private MutableLiveData<String> addAssignmentFailureLiveData;


    private MutableLiveData<List<Assignment>> assignmentResponseLiveData;

    //region Class
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
    //endregion

    private MutableLiveData<LoginResponse> loginResponseLiveData;
    private MutableLiveData<String> loginFailureLiveData;

    private MutableLiveData<List<EnrollmentResponse>> enrollmentResponseLiveData;
    private MutableLiveData<List<FeedbackResponse>> feedbackResponseLiveData;
    private MutableLiveData<List<DoFeedbackResponse>> doFeedbackResponseLiveData;

    private MutableLiveData<List<TrainerResponse>> trainerResponseLiveData;
    public AppRepository() {
        loginResponseLiveData = new MutableLiveData<>();
        loginFailureLiveData = new MutableLiveData<>();

        //region Class init
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
        //endregion

        assignmentResponseLiveData = new MutableLiveData<>();
        addAssignmentResponseLiveData = new MutableLiveData<>();
        addAssignmentFailureLiveData = new MutableLiveData<>();

        enrollmentResponseLiveData =new MutableLiveData<>();

        questionResponseLiveData =new MutableLiveData<>();

        feedbackResponseLiveData=new MutableLiveData<>();

        doFeedbackResponseLiveData=new MutableLiveData<>();
        moduleTraineeTrainerLiveData=new MutableLiveData<>();
        moduleResponseLiveData=new MutableLiveData<>();
        enrollmentResponseLiveData = new MutableLiveData<>();

        //region Question init
        questionResponseLiveData = new MutableLiveData<>();
        getQuestionTopicResponseLiveData = new MutableLiveData<>();
        getQuestionTopicFailureLiveData = new MutableLiveData<>();
        getQuestionInfoResponseLiveData = new MutableLiveData<>();
        getQuestionInfoFailureLiveData = new MutableLiveData<>();
        addQuestionResponseLiveData = new MutableLiveData<>();
        addQuestionFailureLiveData = new MutableLiveData<>();
        updateQuestionResponseLiveData = new MutableLiveData<>();
        updateQuestionFailureLiveData = new MutableLiveData<>();
        deleteQuestionResponseLiveData = new MutableLiveData<>();
        deleteQuestionFailureLiveData = new MutableLiveData<>();
        //endregion

        feedbackResponseLiveData = new MutableLiveData<>();
        doFeedbackResponseLiveData = new MutableLiveData<>();

        //region Module init
        moduleResponseLiveData = new MutableLiveData<>();
        addModuleResponseLiveData = new MutableLiveData<>();
        addModuleFailureLiveData = new MutableLiveData<>();
        deleteModuleResponseLiveData = new MutableLiveData<>();
        deleteModuleFailureLiveData = new MutableLiveData<>();
        getModuleInfoResponseLiveData = new MutableLiveData<>();
        getModuleInfoFailureLiveData = new MutableLiveData<>();
        updateModuleResponseLiveData = new MutableLiveData<>();
        updateModuleFailureLiveData = new MutableLiveData<>();
        //endregion

        trainerResponseLiveData = new MutableLiveData<>();

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

        questionService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuestionService.class);

        feedbackService = new retrofit2.Retrofit.Builder()
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

        userService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
    }

    //region Login
    public void login(LoginRequest loginRequest) {
        loginService.userLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    loginResponseLiveData.postValue(response.body());
                } else {
                    loginFailureLiveData.postValue("Login Failed !");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }
    //endregion

    public LiveData<LoginResponse> getLoginResponseLiveData() {
        return loginResponseLiveData;
    }

    public MutableLiveData<String> getLoginFailureLiveData() {
        return loginFailureLiveData;
    }

    //region Get All Class
    public void classes(String token) {
        classService.getClass("Bearer " + token).enqueue(new Callback<List<ClassResponse>>() {
            @Override
            public void onResponse(Call<List<ClassResponse>> call, Response<List<ClassResponse>> response) {
                if (response.body() != null) {
                    classResponseLiveData.postValue(response.body());
                    Log.i("CLASS RESPONSE LIVE DATA POST VALUE", "123" + classResponseLiveData.toString());
                } else {
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
    //endregion

    //region Get Class Information
    public void getClassInfo(String token, int classId) {
        classService.getClassInfo("Bearer " + token, classId).enqueue(new Callback<ClassResponse>() {
            @Override
            public void onResponse(Call<ClassResponse> call, Response<ClassResponse> response) {
                if (response.body() != null) {
                    getClassInfoResponseLiveData.postValue(response.body());
                } else {
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
    //endregion

    //region Get Class Detail
    public void getClassDetail(String token, int classId) {
        classService.getClassDetail("Bearer " + token, classId).enqueue(new Callback<List<ClassTraineeResponse>>() {
            @Override
            public void onResponse(Call<List<ClassTraineeResponse>> call, Response<List<ClassTraineeResponse>> response) {
                if (response.body() != null) {
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
    //endregion

    //region Update class
    public void updateClass(String token, int classId, ClassRequest classRequest) {
        classService.updateClass("Bearer " + token, classId, classRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
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
    //endregion

    //region Delete class
    public void deleteClass(String token, int classId) {
        classService.deleteClass("Bearer " + token, classId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    deleteClassResponseLiveData.postValue(response.body());
                } else {
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
    //endregion

    //region Add class
    public void addClasses(String token, ClassRequest classRequest) {
        classService.addClass("Bearer " + token, classRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    addClassResponseLiveData.postValue(response.body());
                } else {
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
    //endregion

    //region Get trainer and trainee class
    public void trainerTraineeClass(String token, String role, String username) {
        classService.gettrainerTraineeClass("Bearer " + token, role, username).enqueue(new Callback<List<ClassResponse>>() {
            @Override
            public void onResponse(Call<List<ClassResponse>> call, Response<List<ClassResponse>> response) {
                if (response.body() != null) {
                    trainerTraineeClassResponseLiveData.postValue(response.body());
                } else {
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
    //endregion

    //Get enrollment
    public void enrollments(String token, int classId) {
        enrollmentService.getEnrollment("Bearer " + token, classId).enqueue(new Callback<List<EnrollmentResponse>>() {
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
    public void assignments(String token) {

        assignmentService.getAssignment("Bearer " + token).enqueue(new Callback<List<Assignment>>() {
            @Override
            public void onResponse(Call<List<Assignment>> call, Response<List<Assignment>> response) {
                if (response.body() != null) {
                    assignmentResponseLiveData.postValue(response.body());
                } else {
                    assignmentResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Assignment>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Assignment>> getAssignmentLiveData() {
        return assignmentResponseLiveData;
    }
    public void addAssignment(String token, AssignmentRequest assignmentRequest){
        assignmentService.addAssignment(token,assignmentRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    addAssignmentResponseLiveData.postValue(response.body());
                }else
                    addAssignmentFailureLiveData.postValue("Fail");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                addAssignmentFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<String> getAddAssignmentResponseLiveData() {
        return addAssignmentResponseLiveData;
    }

    public MutableLiveData<String> getAddAssignmentFailureLiveData() {
        return addAssignmentFailureLiveData;
    }

    //region Get Question
    public void questions(String token, int topicId) {
        questionService.getQuestions("Bearer " + token, topicId).enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if (response.body() != null) {
                    questionResponseLiveData.postValue(response.body());
                } else {
                    questionResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Questions>> getQuestionResponseLiveData() {
        return questionResponseLiveData;
    }
    //endregion

    //region Get Question Topic
    public void getQuestionTopic(String token) {
        questionService.getQuestionTopic("Bearer " + token).enqueue(new Callback<List<QuestionTopicResponse>>() {
            @Override
            public void onResponse(Call<List<QuestionTopicResponse>> call, Response<List<QuestionTopicResponse>> response) {
                if (response.body() != null) {
                    getQuestionTopicResponseLiveData.postValue(response.body());
                } else {
                    getQuestionTopicFailureLiveData.postValue("Error to get question topic");
                }
            }

            @Override
            public void onFailure(Call<List<QuestionTopicResponse>> call, Throwable t) {
                getQuestionTopicFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<List<QuestionTopicResponse>> getGetQuestionTopicResponseLiveData() {
        return getQuestionTopicResponseLiveData;
    }

    public MutableLiveData<String> getGetQuestionTopicFailureLiveData() {
        return getQuestionTopicFailureLiveData;
    }
    //endregion

    //region Get Question Info
    public void getQuestionInfo(String token, int questionId) {
        questionService.getQuestionInfo("Bearer " + token, questionId).enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {
                if (response.body() != null && response.isSuccessful()) {
                    getQuestionInfoResponseLiveData.postValue(response.body());
                } else {
                    getQuestionInfoFailureLiveData.postValue("Error to get question information");
                }
            }
            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                getQuestionInfoFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<Questions> getGetQuestionInfoResponseLiveData() {
        return getQuestionInfoResponseLiveData;
    }

    public MutableLiveData<String> getGetQuestionInfoFailureLiveData() {
        return getQuestionInfoFailureLiveData;
    }
    //endregion

    //region Add Question
    public void addQuestion(String token, Questions questionRequest){
        questionService.addQuestion("Bearer " + token, questionRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    addQuestionResponseLiveData.postValue(response.body());
                } else{
                    addQuestionFailureLiveData.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                addQuestionFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<String> getAddQuestionResponseLiveData() {
        return addQuestionResponseLiveData;
    }

    public MutableLiveData<String> getAddQuestionFailureLiveData() {
        return addQuestionFailureLiveData;
    }
    //endregion

    //region Delete Question
    public void deleteQuestion(String token, int questionId){
        questionService.deleteQuestion("Bearer " + token,questionId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    deleteQuestionResponseLiveData.postValue(response.body());
                } else{
                    deleteQuestionFailureLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                deleteQuestionFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<String> getDeleteQuestionResponseLiveData() {
        return deleteQuestionResponseLiveData;
    }

    public MutableLiveData<String> getDeleteQuestionFailureLiveData() {
        return deleteQuestionFailureLiveData;
    }
    //endregion

    //region Update Question
    public void updateQuestion(String token,int questionId, Questions questionRequest){
        questionService.updateQuestion("Bearer " + token,questionId,questionRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    updateQuestionResponseLiveData.postValue(response.body());
                }else{
                    updateQuestionFailureLiveData.postValue("Update Question Fail");
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                updateQuestionFailureLiveData.postValue(t.getLocalizedMessage());
            }
        });
    }

    public MutableLiveData<String> getUpdateQuestionResponseLiveData() {
        return updateQuestionResponseLiveData;
    }

    public MutableLiveData<String> getUpdateQuestionFailureLiveData() {
        return updateQuestionFailureLiveData;
    }
    //endregion

    //Get Feedback
    public void feedbacks(String token) {
        feedbackService.getFeedbacks(token).enqueue(new Callback<List<FeedbackResponse>>() {
            @Override
            public void onResponse(Call<List<FeedbackResponse>> call, Response<List<FeedbackResponse>> response) {
                if (response.body() != null) {
                    feedbackResponseLiveData.postValue(response.body());
                } else {
                    feedbackResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<FeedbackResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<FeedbackResponse>> getFeedbackResponseLiveData() {
        return feedbackResponseLiveData;
    }

    //Get do feedback
    public void doFeedbacks(String token, String traineeId) {
        feedbackService.getFeedbacksTraineeDo(token, traineeId).enqueue(new Callback<List<DoFeedbackResponse>>() {
            @Override
            public void onResponse(Call<List<DoFeedbackResponse>> call, Response<List<DoFeedbackResponse>> response) {
                if (response.body() != null) {
                    doFeedbackResponseLiveData.postValue(response.body());
                } else {
                    doFeedbackResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<DoFeedbackResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<DoFeedbackResponse>> getDoFeedbackResponseLiveData() {
        return doFeedbackResponseLiveData;
    }

    //region Get module
    public void modules(String token) {
        moduleService.getModules(token).enqueue(new Callback<List<ModuleResponse>>() {
            @Override
            public void onResponse(Call<List<ModuleResponse>> call, Response<List<ModuleResponse>> response) {
                if (response.body() != null) {
                    moduleResponseLiveData.postValue(response.body());
                } else {
                    moduleResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<ModuleResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<ModuleResponse>> getModuleResponseLiveData() {
        return moduleResponseLiveData;
    }
    //endregion

    //Get module trainee or trainer
    public void moduleTraineeTrainer(String token, String role, String username){
        moduleService.getModuleTraineeTrainer(token,role,username).enqueue(new Callback<List<ModuleResponse>>() {
            @Override
            public void onResponse(Call<List<ModuleResponse>> call, Response<List<ModuleResponse>> response) {
                if(response.body()!=null){
                    moduleTraineeTrainerLiveData.postValue(response.body());
                }
                else {
                    moduleTraineeTrainerLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<ModuleResponse>> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<List<ModuleResponse>> getModuleTraineeTrainerResponseLiveData(){
        return moduleTraineeTrainerLiveData;
    }


    //region Add module
    public void addModule(String token, ModuleRequest moduleRequest) {
        moduleService.addModule(token, moduleRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    addModuleResponseLiveData.postValue(response.body());
                } else
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
    //endregion

    //region Delete Module
    public void deleteModule(String token, int moduleId) {
        moduleService.deleteModule(token, moduleId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    deleteModuleResponseLiveData.postValue(response.body());
                } else
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
    //endregion

    //region Get Module information
    public void getModuleInfo(String token, int moduleId) {
        moduleService.getModuleInfo(token, moduleId).enqueue(new Callback<ModuleResponse>() {
            @Override
            public void onResponse(Call<ModuleResponse> call, Response<ModuleResponse> response) {
                if (response.body() != null) {
                    getModuleInfoResponseLiveData.postValue(response.body());
                } else {
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
    //endregion

    //region Update Module
    public void updateModule(String token, int moduleId, ModuleRequest moduleRequest) {
        moduleService.updateModule(token, moduleId, moduleRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
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

    //Get module
    public void trainer(String token){
        userService.getTrainer(token).enqueue(new Callback<List<TrainerResponse>>() {
            @Override
            public void onResponse(Call<List<TrainerResponse>> call, Response<List<TrainerResponse>> response) {
                if(response.body()!=null){
                    trainerResponseLiveData.postValue(response.body());
                }
                else {
                    trainerResponseLiveData.postValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<List<TrainerResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<TrainerResponse>> getTrainerResponseLiveData(){
        return trainerResponseLiveData;
    }
    //endregion
}


