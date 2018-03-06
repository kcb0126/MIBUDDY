package com.kcb0126.developer.mibuddy.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by developer on 3/6/2018.
 */

public class EnglishQuestion implements Parcelable {

    private String DBID;
    private String questionText;
    private String category;
    private int audio;

    private Cursor cursor;

    public EnglishQuestion() {};

    public boolean readQuestionsCategory(Context context, String category){

        boolean found = true;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO
        };

        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY + " = ?";
        String[] selectionArgs = {category};

        cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ENGLISH_QUESTION, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        if(cursor.moveToFirst() && cursor.getCount() > 0){
            setDBID(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry._ID)));
            setQuestionText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION)));
            setCategory(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY)));
            setAudio(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO)));
        }else{
            found = false;
        }

        return found;
    }

    public boolean readQuestionID(Context context, String ID){

        boolean found = true;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO
        };

        String selection = DataBaseContract.DataBaseEntry._ID + " = ?";
        String[] selectionArgs = {ID};
        cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ENGLISH_QUESTION, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        if(cursor.moveToFirst() && cursor.getCount() > 0){
            setDBID(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry._ID)));
            setQuestionText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION)));
            setCategory(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY)));
            setAudio(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO)));
        }else{
            found = false;
        }

        return found;
    }

    public boolean readAllQuestions(Context context){

        boolean found = true;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO
        };

        cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ENGLISH_QUESTION, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        if(cursor.moveToFirst() && cursor.getCount() > 0){
            setDBID(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry._ID)));
            setQuestionText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION)));
            setCategory(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY)));
            setAudio(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO)));
        }else{
            found = false;
        }

        return found;
    }

    public boolean moveCursor() {

        boolean next = true;

        if(cursor.moveToNext() && cursor.getCount() > 0) {
            setDBID(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry._ID)));
            setQuestionText(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION)));
            setCategory(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY)));
            setAudio(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO)));
        }
        else
            next = false;
        return next;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.DBID);
        dest.writeString(this.questionText);
        dest.writeString(this.category);
        dest.writeInt(this.audio);
    }

    protected EnglishQuestion(Parcel in) {
        this.DBID = in.readString();
        this.questionText = in.readString();
        this.category = in.readString();
        this.audio = in.readInt();
    }

    public static final Creator<EnglishQuestion> CREATOR = new Creator<EnglishQuestion>() {
        @Override
        public EnglishQuestion createFromParcel(Parcel source) {
            return new EnglishQuestion(source);
        }

        @Override
        public EnglishQuestion[] newArray(int size) {
            return new EnglishQuestion[size];
        }
    };

    public String getDBID() {
        return DBID;
    }

    public void setDBID(String DBID) {
        this.DBID = DBID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }
}
