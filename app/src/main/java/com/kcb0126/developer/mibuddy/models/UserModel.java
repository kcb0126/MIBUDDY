package com.kcb0126.developer.mibuddy.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by developer on 3/13/2018.
 */

public class UserModel {
    private String mEmail;
    private String mPassword;
    private String mUsername;
    private String mGender;
    private int mAge;
    private String mNationality;
    private String mLanguage;
    private String mOccupation;
    private String mAreas;
    private String mHerefor;
    private String mAboutme;

    private static UserModel mInstance = null;

    public static UserModel instance() {
        if(mInstance == null) {
            mInstance = new UserModel();
        }
        return mInstance;
    }

    public UserModel() {
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setmPassword(String password) {
        this.mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        this.mGender = gender;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        this.mAge = age;
    }

    public String getNationality() {
        return mNationality;
    }

    public void setNationality(String nationality) {
        this.mNationality = nationality;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        this.mLanguage = language;
    }

    public String getOccupation() {
        return mOccupation;
    }

    public void setOccupation(String occupation) {
        this.mOccupation = occupation;
    }

    public String getAreas() {
        return mAreas;
    }

    public void setAreas(String areas) {
        this.mAreas = areas;
    }

    public String getHerefor() {
        return mHerefor;
    }

    public void setHerefor(String herefor) {
        this.mHerefor = herefor;
    }

    public String getAboutme() {
        return mAboutme;
    }

    public void setAboutme(String aboutme) {
        this.mAboutme = aboutme;
    }

    public void parseFromJSON(JSONObject data) {
        try {
            if(data.has("username")) {
                setUsername(data.getString("username"));
            }
            if(data.has("gender")) {
                setGender(data.getString("gender"));
            }
            if(data.has("age")) {
                setAge(data.getInt("age"));
            }
            if(data.has("nationality")) {
                setNationality(data.getString("nationality"));
            }
            if(data.has("language")) {
                setNationality(data.getString("nationality"));
            }
            if(data.has("occupation")) {
                setOccupation(data.getString("occupation"));
            }
            if(data.has("areas")) {
                setAreas(data.getString("areas"));
            }
            if(data.has("herefor")) {
                setHerefor(data.getString("herefor"));
            }
            if(data.has("aboutme")) {
                setAboutme(data.getString("aboutme"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
