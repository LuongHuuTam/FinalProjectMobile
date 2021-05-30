package com.example.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assignment {
    @SerializedName("classId")
    @Expose
    private int classID;

    @SerializedName("moduleID")
    @Expose
    private int moduleID;

    @SerializedName("trainerID")
    @Expose
    private String trainerID;

    @SerializedName("moduleName")
    @Expose
    private String moduleName;

    @SerializedName("className")
    @Expose
    private String className;

    @SerializedName("trainerName")
    @Expose
    private String trainerName;

    @SerializedName("registrationCode")
    @Expose
    private String registrationCode;

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
