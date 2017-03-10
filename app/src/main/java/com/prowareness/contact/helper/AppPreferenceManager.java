package com.prowareness.contact.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferenceManager {

    private static AppPreferenceManager instance;

    private transient SharedPreferences prefs;

    private AppPreferenceManager(Context context) {
        prefs = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
    }

    public static AppPreferenceManager getInstance(Context context) {
        if (instance == null)
            instance = new AppPreferenceManager(context);
        return instance;
    }

    public void saveString(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

}
