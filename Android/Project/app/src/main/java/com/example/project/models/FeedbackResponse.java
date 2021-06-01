package com.example.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedbackResponse {
    @SerializedName("feedbackID")
    @Expose
    private int feedbackID;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("adminID")
    @Expose
    private String adminID;

    @SerializedName("typeFeedbackID")
    @Expose
    private int typeFeedbackID;

    @SerializedName("typeFeedbackName")
    @Expose
    private String typeFeedbackName;

//    @SerializedName("questions")
//    @Expose
//    private List<QuestionResponse> questions;

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public int getTypeFeedbackID() {
        return typeFeedbackID;
    }

    public void setTypeFeedbackID(int typeFeedbackID) {
        this.typeFeedbackID = typeFeedbackID;
    }

    public String getTypeFeedbackName() {
        return typeFeedbackName;
    }

    public void setTypeFeedbackName(String typeFeedbackName) {
        this.typeFeedbackName = typeFeedbackName;
    }

//    public List<QuestionResponse> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(List<QuestionResponse> questions) {
//        this.questions = questions;
//    }
}
