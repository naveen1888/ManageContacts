package com.prowareness.contact.bean;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

public class Result implements Comparable<Result> {

    @SerializedName("name")
    private String name;

    @SerializedName("uid")
    private int uid;

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", uid=" + uid +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getUid() {
        return uid;
    }

    @Override
    public int compareTo(@NonNull Result result) {

        return name.compareTo(result.name);
    }
}
