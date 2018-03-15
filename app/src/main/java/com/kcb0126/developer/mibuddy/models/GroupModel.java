package com.kcb0126.developer.mibuddy.models;

import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by developer on 3/15/2018.
 */

public class GroupModel {

    // constructors

    public GroupModel() {

    }

    public GroupModel(int id, String name, String leader, int members) {
        mId = id;
        mName = name;
        mLeader = leader;
        mMembers = members;
    }

    // getters and setters

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLeader() {
        return mLeader;
    }

    public void setLeader(String leader) {
        mLeader = leader;
    }

    public int getMembers() {
        return mMembers;
    }

    public void setMembers(int members) {
        mMembers = members;
    }

    // copy method
    public GroupModel copy() {
        return new GroupModel(mId, mName, mLeader, mMembers);
    }

    // parse from JSONObject
    @Nullable
    public static GroupModel parseFromJSON(JSONObject data) {
        GroupModel model = new GroupModel();
        try {
            if(data.has("id")) {
                model.setId(data.getInt("id"));
            }
            if(data.has("name")) {
                model.setName(data.getString("name"));
            }
            if(data.has("leader")) {
                model.setLeader(data.getString("leader"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return model;
    }

    // properties
    private int mId;
    private String mName;
    private String mLeader;
    private int mMembers;
}
