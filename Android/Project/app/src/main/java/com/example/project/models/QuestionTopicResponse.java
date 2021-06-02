package com.example.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionTopicResponse {
    @SerializedName("topicId")
    @Expose
    private int topicId;

    @SerializedName("name")
    @Expose
    private String name;

    public int getTopicId() {
        return topicId;
    }

    public String getName() {
        return name;
    }
}
