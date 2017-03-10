package com.prowareness.contact.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

public class ContactRes {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private ArrayList<Result> results;

    @Override
    public String toString() {
        return "ContactRes{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", results=" + results +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Result> getResults() {
        return results;
    }
}
