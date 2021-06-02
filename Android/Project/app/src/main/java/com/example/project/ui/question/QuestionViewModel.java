package com.example.project.ui.question;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.project.models.Questions;
import com.example.project.models.QuestionTopicResponse;
import com.example.project.network.repositories.AppRepository;

import java.util.List;

public class QuestionViewModel extends ViewModel {
    private AppRepository appRepository = new AppRepository();
    private LiveData<List<Questions>> questionResponseLiveData;

    private LiveData<List<QuestionTopicResponse>> questionTopicResponseLiveData;
    private LiveData<String> questionTopicFailureLiveData;

    private LiveData<Questions> questionInfoResponseLiveData;
    private LiveData<String> questionInfoFailureLiveData;

    private LiveData<String> addQuestionResponseLiveData;
    private LiveData<String> addQuestionFailureLiveData;

    private LiveData<String> deleteQuestionResponseLiveData;
    private LiveData<String> deleteQuestionFailureLiveData;

    private LiveData<String> updateQuestionResponseLiveData;
    private LiveData<String> updateQuestionFailureLiveData;


    public QuestionViewModel() {
        super();
        questionResponseLiveData=appRepository.getQuestionResponseLiveData();

        questionTopicResponseLiveData = appRepository.getGetQuestionTopicResponseLiveData();
        questionTopicFailureLiveData = appRepository.getGetQuestionTopicFailureLiveData();

        questionInfoResponseLiveData = appRepository.getGetQuestionInfoResponseLiveData();
        questionInfoFailureLiveData = appRepository.getGetQuestionInfoFailureLiveData();

        addQuestionResponseLiveData = appRepository.getAddQuestionResponseLiveData();
        addQuestionFailureLiveData = appRepository.getAddQuestionFailureLiveData();

        deleteQuestionResponseLiveData = appRepository.getDeleteQuestionResponseLiveData();
        deleteQuestionFailureLiveData = appRepository.getDeleteQuestionFailureLiveData();

        updateQuestionResponseLiveData = appRepository.getUpdateQuestionResponseLiveData();
        updateQuestionFailureLiveData = appRepository.getUpdateQuestionFailureLiveData();

    }

    public LiveData<List<Questions>> getQuestionResponseLiveData() {
        return questionResponseLiveData;
    }

    public LiveData<List<QuestionTopicResponse>> getQuestionTopicResponseLiveData() {
        return questionTopicResponseLiveData;
    }

    public LiveData<String> getQuestionTopicFailureLiveData() {
        return questionTopicFailureLiveData;
    }

    public LiveData<Questions> getQuestionInfoResponseLiveData() {
        return questionInfoResponseLiveData;
    }

    public LiveData<String> getQuestionInfoFailureLiveData() {
        return questionInfoFailureLiveData;
    }

    public LiveData<String> getAddQuestionResponseLiveData() {
        return addQuestionResponseLiveData;
    }

    public LiveData<String> getAddQuestionFailureLiveData() {
        return addQuestionFailureLiveData;
    }

    public LiveData<String> getDeleteQuestionResponseLiveData() {
        return deleteQuestionResponseLiveData;
    }

    public LiveData<String> getDeleteQuestionFailureLiveData() {
        return deleteQuestionFailureLiveData;
    }

    public LiveData<String> getUpdateQuestionResponseLiveData() {
        return updateQuestionResponseLiveData;
    }

    public LiveData<String> getUpdateQuestionFailureLiveData() {
        return updateQuestionFailureLiveData;
    }

    public void questions(String token, int topicId){
        appRepository.questions(token,topicId);
    }

    public void getQuestionTopic(String token){appRepository.getQuestionTopic(token);}

    public void getQuestionInfo(String token, int questionId){appRepository.getQuestionInfo(token,questionId);}

    public void addQuestion(String token,Questions questionRequest){appRepository.addQuestion(token, questionRequest);}

    public void deleteQuestion(String token, int questionId){appRepository.deleteQuestion(token, questionId);}

    public void updateQuestion(String token, int questionId, Questions questionRequest){appRepository.updateQuestion(token, questionId,questionRequest);}
}
