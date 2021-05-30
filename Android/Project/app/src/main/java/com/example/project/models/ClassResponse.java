package com.example.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassResponse {
    @SerializedName("classID")
    @Expose
    private int classID;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("capacity")
    @Expose
    private int capacity;

    @SerializedName("startTime")
    @Expose
    private String startTime;

    @SerializedName("endTime")
    @Expose
    private String endTime;

   @SerializedName("enrollmentsCount")
    @Expose
    private int enrollmentsCount;

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public int getEnrollmentsCount() {
        return enrollmentsCount;
    }

    public void setEnrollmentsCount(int enrollmentsCount) {
        this.enrollmentsCount = enrollmentsCount;
    }

    @Override
    public String toString() {
        return name;
    }
}
