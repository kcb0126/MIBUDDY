package com.kcb0126.developer.mibuddy.models;

import android.support.annotation.Nullable;
import android.view.MotionEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by developer on 3/15/2018.
 */

public class ChatModel {

    // properties
    private String mUsername;
    private boolean mIsYou;
    private ArrayList<MessageModel> mMessages;

    // constructors
    public ChatModel() {
        mMessages = new ArrayList<>();
    }

    public ChatModel(String username, boolean isYou, ArrayList<MessageModel> messages) {
        mUsername = username;
        mIsYou = isYou;
        mMessages = new ArrayList<>();
        for(MessageModel message : messages) {
            mMessages.add(message.copy());
        }
    }

    // getters and setters

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public boolean getIsYou() {
        return mIsYou;
    }

    public void setIsYou(boolean isYou) {
        mIsYou = isYou;
    }

    public ArrayList<MessageModel> getMessages() {
        ArrayList<MessageModel> messages = new ArrayList<>();
        for(MessageModel message : mMessages) {
            messages.add(message.copy());
        }
        return messages;
    }

    public void setMessages(ArrayList<MessageModel> messages) {
        mMessages.clear();
        for(MessageModel message : messages) {
            mMessages.add(message.copy());
        }
    }

    // clone method named "copy"
    public ChatModel copy() {
        ArrayList<MessageModel> messages = new ArrayList<>();
        for(MessageModel message : mMessages) {
            messages.add(message.copy());
        }
        return new ChatModel(mUsername, mIsYou, messages);
    }

    // parse from JSONObject
    @Nullable
    public static ChatModel parseFromJSON(JSONObject data) {
        ChatModel model = new ChatModel();
        try {
            if(data.has("username")) {
                model.setUsername(data.getString("username"));
            }
            if(data.has("isyou")) {
                model.setIsYou(data.getBoolean("isyou"));
            }
            if(data.has("messages")) {
                JSONArray msgArray = data.getJSONArray("messages");
                ArrayList<MessageModel> messages = new ArrayList<>();
                for(int i = 0; i < msgArray.length(); i++) {
                    JSONObject msgObj = msgArray.getJSONObject(i);
                    MessageModel message = MessageModel.parseFromJSON(msgObj);
                    if(message == null) {
                        continue;
                    }
                    messages.add(message);
                }
                model.setMessages(messages);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return model;
    }
}
