package com.example.project.models.class_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClassTraineeResponse {
    @SerializedName("traineeUsername")
    @Expose
    private String traineeUsername;

    @SerializedName("traineeName")
    @Expose
    private String TraineeName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private String address;

    public String getTraineeUsername() {
        return traineeUsername;
    }

    public String getTraineeName() {
        return TraineeName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
