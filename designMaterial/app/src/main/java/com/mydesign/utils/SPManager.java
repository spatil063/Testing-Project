package com.mydesign.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.mydesign.BaseApp;

public class SPManager {
    private static SPManager myManager;
    private static SharedPreferences sharedPreferences;


    private SPManager(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static SPManager getInstance() {
        return myManager != null ? myManager : new SPManager(BaseApp.getContext());
    }

    private String retrieveString(String key) {
        return sharedPreferences.getString(key, "");
    }

    private int retrieveInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }



}