package com.example.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnrollmentResponse {
    @SerializedName("classId")
    @Expose
    private int classId;

    @SerializedName("className")
    @Expose
    private String className;

    @SerializedName("traineeId")
    @Expose
    private String traineeId;

    @SerializedName("traineeName")
    @Expose
    private String traineeName;

    public int getClassID() {
        return classId;
    }

    public void setClassID(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }
}
