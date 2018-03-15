package com.kcb0126.developer.mibuddy.models;

import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by developer on 3/15/2018.
 */

public class MessageModel {

    // properties
    private int mId;
    private String mMessage;

    // constructors
    public MessageModel() {

    }

    public MessageModel(int id, String message) {
        mId = id;
        mMessage = message;
    }

    // getters and setters

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    // clone method named "copy"
    public MessageModel copy() {
        return new MessageModel(mId, mMessage);
    }

    // parse from JSONObject
    @Nullable
    public static MessageModel parseFromJSON(JSONObject data) {
        MessageModel model = new MessageModel();
        try {
            if(data.has("id")) {
                model.setId(data.getInt("id"));
            }
            if(data.has("message")) {
                model.setMessage(data.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return model;
    }
}
