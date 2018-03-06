package com.kcb0126.developer.mibuddy.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by developer on 3/6/2018.
 */

public class QuestionxAnswer implements Parcelable {

    private String AID; //id for the answer
    private String QID; //id for the question

    private Cursor cursor;

    public QuestionxAnswer() {}

    public boolean readQuestionID(Context context, String id){

        boolean found = true;

        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION_ID,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER_ID
        };

        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION_ID + " = ?";

        String[] selectionArgs = {id};

        cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_QUESTION_X_ANSWER, // tabla
                projection, // columnas
                selection, // where
                selectionArgs, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );

        if(cursor.moveToFirst() && cursor.getCount() > 0){
            setQID(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION_ID)));
            setAID(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER_ID)));
        }
        else
            found = false;

        return found;
    }

    public boolean moveCursor() {

        boolean next = true;

        if(cursor.moveToNext() && cursor.getCount() > 0) {
            setQID(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION_ID)));
            setAID(cursor.getString(cursor.getColumnIndexOrThrow(
                    DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER_ID)));
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
        dest.writeString(this.AID);
        dest.writeString(this.QID);
    }

    protected QuestionxAnswer(Parcel in) {
        this.AID = in.readString();
        this.QID = in.readString();
    }

    public static final Parcelable.Creator<ChineseQuestion> CREATOR = new Parcelable.Creator<ChineseQuestion>() {
        @Override
        public ChineseQuestion createFromParcel(Parcel source) {
            return new ChineseQuestion(source);
        }

        @Override
        public ChineseQuestion[] newArray(int size) {
            return new ChineseQuestion[size];
        }
    };

    public String getAID() {
        return AID;
    }

    public void setAID(String AID) {
        this.AID = AID;
    }

    public String getQID() {
        return QID;
    }

    public void setQID(String QID) {
        this.QID = QID;
    }
}
