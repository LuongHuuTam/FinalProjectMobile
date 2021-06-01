package com.example.project.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project.models.LoginResponse;
import com.google.gson.Gson;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS = "APP_SETTINGS";

    // properties
    private static final String LOGIN_RESPONSE = "LoginResponse";

    private static final String CLASS_INFO_RESPONSE = "ClassInfoResponse";
    // other properties...


    private SharedPreferencesManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static LoginResponse getLoginResponseValue(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(LOGIN_RESPONSE , null);
        return gson.fromJson(json, LoginResponse.class);
    }

    public static void setLoginResponseValue(Context context, LoginResponse loginResponse) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();

        Gson gson = new Gson();
        String json = gson.toJson(loginResponse);
        editor.putString(LOGIN_RESPONSE , json);
        editor.commit();
    }

//    public static ClassResponse getClassInfoResponseValue(Context context){
//        Gson gson = new Gson();
//        String json = getSharedPreferences(context).getString(CLASS_INFO_RESPONSE,null);
//        return gson.fromJson(json,ClassResponse.class);
//    }
//
//    public static void setClassInfoResponseValue(Context context, ClassResponse classResponse){
//        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(classResponse);
//        editor.putString(CLASS_INFO_RESPONSE,json);
//        editor.commit();
//    }

    // other getters/setters
}
