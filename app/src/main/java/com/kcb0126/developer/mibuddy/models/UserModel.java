package com.kcb0126.developer.mibuddy.models;

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

    public void setmAboutme(String aboutme) {
        this.mAboutme = aboutme;
    }
}
