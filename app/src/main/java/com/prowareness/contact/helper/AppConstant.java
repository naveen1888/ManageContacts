package com.prowareness.contact.helper;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prowareness.contact.bean.Result;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

public class AppConstant {

    public static final String PREF_LIST = "list";

    public static ArrayList<Result> getUpdatedList(Context context) {
        String json = AppPreferenceManager.getInstance(context).getString(PREF_LIST, null);
        Type type = new TypeToken<ArrayList<Result>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

}
