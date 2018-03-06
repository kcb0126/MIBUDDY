package com.kcb0126.developer.mibuddy.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by developer on 3/6/2018.
 */

public class ChineseAnswer implements Parcelable {

    private String DBID;
    private String answerText;
    private int audio;

    private Cursor cursor;

    public ChineseAnswer() {};

    public boolean readAnswerID(Context context, String ID){

        boolean found = true;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.DataBaseEntry._ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO
        };

        String selection = DataBaseContract.DataBaseEntry._ID + " = ?";
        String[] selectionArgs = {ID};

        cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_CHINESE_ANSWER, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        if(cursor.moveToFirst() && cursor.getCount() > 0){
            setAnswerText(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER)));
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
            setAnswerText(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER)));
            setAudio(cursor.getInt(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO)));
        }
        else
            next = false;
        return next;
    }



    public String getDBID() {
        return DBID;
    }

    public void setDBID(String DBID) {
        this.DBID = DBID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.DBID);
        dest.writeString(this.answerText);
        dest.writeInt(this.audio);
    }

    protected ChineseAnswer(Parcel in) {
        this.DBID = in.readString();
        this.answerText = in.readString();
        this.audio = in.readInt();
    }

    public static final Creator<ChineseAnswer> CREATOR = new Creator<ChineseAnswer>() {
        @Override
        public ChineseAnswer createFromParcel(Parcel source) {
            return new ChineseAnswer(source);
        }

        @Override
        public ChineseAnswer[] newArray(int size) {
            return new ChineseAnswer[size];
        }
    };
}