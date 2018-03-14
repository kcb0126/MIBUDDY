package com.kcb0126.developer.mibuddy.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by developer on 3/14/2018.
 */

public class PreferenceManager {
    private final String KEY_EMAIL = "email";
    private final String KEY_PASSWORD = "password";
    private final String KEY_TOKEN = "token";

    private final String FILE_NAME = "mibuddy.pref";

    private static PreferenceManager mInstance = null;

    public static PreferenceManager instance() {
        if(mInstance == null) {
            mInstance = new PreferenceManager();
        }
        return mInstance;
    }

    public void putEmail(Context context, String email) {
        put(context, KEY_EMAIL, email);
    }

    public String getEmail(Context context) {
        return get(context, KEY_EMAIL, "");
    }

    public void putPassword(Context context, String password) {
        put(context, KEY_PASSWORD, password);
    }

    public String getPassword(Context context) {
        return get(context, KEY_PASSWORD, "");
    }

    public void putToken(Context context, String token) {
        put(context, KEY_TOKEN, token);
    }

    public String getToken(Context context) {
        return get(context, KEY_TOKEN, "");
    }

    private void put(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String get(Context context, String key, String defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        try {
            return pref.getString(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
