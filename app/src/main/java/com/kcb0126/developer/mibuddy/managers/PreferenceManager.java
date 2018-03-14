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

    private final String KEY_TOPIC = "topic";

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


    public int getTopic(Context context) {
        return get(context, KEY_TOPIC, 0);
    }

    public void putTopic(Context context, int topic) {
        put(context, KEY_TOPIC, topic);
    }

    private void put(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private void put(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private String get(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        try {
            return preferences.getString(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private int get(Context context, String key, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        try {
            return preferences.getInt(key, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
