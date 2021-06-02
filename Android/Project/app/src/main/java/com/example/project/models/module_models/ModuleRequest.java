package com.example.project.models.module_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModuleRequest {
    @SerializedName("adminID")
    @Expose
    private String adminID;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("startTime")
    @Expose
    private String startTime;

    @SerializedName("endTime")
    @Expose
    private String endTime;

    @SerializedName("feedbackStartTime")
    @Expose
    private String feedbackStartTime;

    @SerializedName("feedbackEndTime")
    @Expose
    private String feedbackEndTime;

    @SerializedName("feedbackTitle")
    @Expose
    private String feedbackTitle;

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setFeedbackStartTime(String feedbackStartTime) {
        this.feedbackStartTime = feedbackStartTime;
    }

    public void setFeedbackEndTime(String feedbackEndTime) {
        this.feedbackEndTime = feedbackEndTime;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }
}
