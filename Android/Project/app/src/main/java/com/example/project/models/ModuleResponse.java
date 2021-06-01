package com.example.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModuleResponse {

    @SerializedName("moduleID")
    @Expose
    private int moduleID;

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

    @SerializedName("feedbackID")
    @Expose
    private int feedbackID;
    @SerializedName("feedbackTitle")
    @Expose
    private String feedbackTitle;

    @SerializedName("feedbackStartTime")
    @Expose
    private String feedbackStartTime;

    @SerializedName("feedbackEndTime")
    @Expose
    private String feedbackEndTime;

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackStartTime() {
        return feedbackStartTime;
    }

    public void setFeedbackStartTime(String feedbackStartTime) {
        this.feedbackStartTime = feedbackStartTime;
    }

    public String getFeedbackEndTime() {
        return feedbackEndTime;
    }

    public void setFeedbackEndTime(String feedbackEndTime) {
        this.feedbackEndTime = feedbackEndTime;
    }
}
