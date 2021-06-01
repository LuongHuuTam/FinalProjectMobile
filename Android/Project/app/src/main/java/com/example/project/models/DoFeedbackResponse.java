package com.example.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoFeedbackResponse {
    @SerializedName("feedbackTitle")
    @Expose
    private String feedbackTitle;

    @SerializedName("classID")
    @Expose
    private int classID;

    @SerializedName("className")
    @Expose
    private String className;

    @SerializedName("moduleId")
    @Expose
    private int moduleId;

    @SerializedName("moduleName")
    @Expose
    private String moduleName;

    @SerializedName("endTime")
    @Expose
    private String endTime;

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private boolean status;
}
