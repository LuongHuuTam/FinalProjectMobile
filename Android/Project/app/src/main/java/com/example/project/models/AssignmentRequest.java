package com.example.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignmentRequest {
    @SerializedName("classId")
    @Expose
    private Integer classId;
    @SerializedName("moduleID")
    @Expose
    private Integer moduleID;
    @SerializedName("trainerID")
    @Expose
    private String trainerID;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getModuleID() {
        return moduleID;
    }

    public void setModuleID(Integer moduleID) {
        this.moduleID = moduleID;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }
}
