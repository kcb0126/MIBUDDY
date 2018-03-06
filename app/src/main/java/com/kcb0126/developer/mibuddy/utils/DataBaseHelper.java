package com.kcb0126.developer.mibuddy.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by developer on 3/6/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "MIBUDDYDB.db";
    private Context context;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {


        db.execSQL(DataBaseContract.SQL_CREATE_USER);
        db.execSQL(DataBaseContract.SQL_CREATE_ENGLISH_QUESTION);
        db.execSQL(DataBaseContract.SQL_CREATE_ENGLISH_ANSWER);
        db.execSQL(DataBaseContract.SQL_CREATE_CHINESE_QUESTION);
        db.execSQL(DataBaseContract.SQL_CREATE_CHINESE_ANSWER);
        db.execSQL(DataBaseContract.SQL_CREATE_QUESTION_X_ANSWER);


//      Input Users (" ")

        String mCSVfile = "users.csv";
        AssetManager manager = context.getAssets();
        InputStream inputStream1 = null;
        try {
            inputStream1 = manager.open(mCSVfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream1));
        String line = "";
        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length != 4) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }

                insertUser(db, (columns[0]), columns[1], columns[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();


//      Input English Questions (2)

        String mCSVfile2 = "english_question.csv";
        AssetManager manager2 = context.getAssets();
        InputStream inputStream2 = null;
        try {
            inputStream2 = manager2.open(mCSVfile2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer2 = new BufferedReader(new InputStreamReader(inputStream2));
        String line2 = "";
        db.beginTransaction();
        try {
            while ((line2 = buffer2.readLine()) != null) {
                String[] columns2 = line2.split(",");
                if (columns2.length != 4) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }

                insertEnglishQuestion(db, (columns2[0]), columns2[1], columns2[2], Integer.parseInt(columns2[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

        //      Input Chinese Questions (3)

        String mCSVfile3 = "chinese_prequestion.csv";
        AssetManager manager3 = context.getAssets();
        InputStream inputStream3 = null;
        try {
            inputStream3 = manager3.open(mCSVfile3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer3 = new BufferedReader(new InputStreamReader(inputStream3));
        String line3 = "";
        db.beginTransaction();
        try {
            while ((line3 = buffer3.readLine()) != null) {
                String[] columns3 = line3.split(",");
                if (columns3.length != 4) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }

                insertChineseQuestion(db, (columns3[0]), columns3[1], columns3[2], Integer.parseInt(columns3[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();


        //      Input English Answers (4)

        String mCSVfile4 = "english_answer.csv";
        AssetManager manager4 = context.getAssets();
        InputStream inputStream4 = null;
        try {
            inputStream4 = manager4.open(mCSVfile4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer4 = new BufferedReader(new InputStreamReader(inputStream4));
        String line4 = "";
        db.beginTransaction();
        try {
            while ((line4 = buffer4.readLine()) != null) {
                String[] columns4 = line4.split(",");
                if (columns4.length != 3) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }

                insertEnglishAnswer(db, (columns4[0]), columns4[1], Integer.parseInt(columns4[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();


        //      Input Chinese Answers (5)

        String mCSVfile5 = "chinese_preanswer.csv";
        AssetManager manager5 = context.getAssets();
        InputStream inputStream5 = null;
        try {
            inputStream5 = manager5.open(mCSVfile5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer5 = new BufferedReader(new InputStreamReader(inputStream5));
        String line5 = "";
        db.beginTransaction();
        try {
            while ((line5 = buffer5.readLine()) != null) {
                String[] columns5 = line5.split(",");
                if (columns5.length != 3) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }

                insertChineseAnswer(db, (columns5[0]), columns5[1], Integer.parseInt(columns5[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();


        //      Input QuestionXAnswers (6)

        String mCSVfile6 = "Question_X_Answer.csv";
        AssetManager manager6 = context.getAssets();
        InputStream inputStream6 = null;
        try {
            inputStream6 = manager6.open(mCSVfile6);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader buffer6 = new BufferedReader(new InputStreamReader(inputStream6));
        String line6 = "";
        db.beginTransaction();
        try {
            while ((line6 = buffer6.readLine()) != null) {
                String[] columns6 = line6.split(",");
                if (columns6.length != 3) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }

                insertQuestionXAnswer(db, columns6[0], columns6[1], columns6[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    //inserts a user into the DB, with the specified attributes
    public void insertUser(SQLiteDatabase db, String email, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry._ID, email);
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_USERNAME, username);
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_PASSWORD, password);

        db.insert(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.TABLE_NAME_USER, null, values);
    }

    //inserts an English question into the DB, with the specified attributes
    public void insertEnglishQuestion(SQLiteDatabase db, String id, String question, String category, int audio) {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry._ID, id);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION, question);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY, category);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO, audio);

        db.insert(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.TABLE_NAME_ENGLISH_QUESTION, null, values);
    }

    //    inserts an English answer into the DB, with the specified attributes
    public void insertEnglishAnswer(SQLiteDatabase db, String id, String answer, int audio) {
        ContentValues values = new ContentValues();
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry._ID, id);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER, answer);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO, audio);

        db.insert(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.TABLE_NAME_ENGLISH_ANSWER, null, values);
    }

    //inserts a Chinese question into the DB, with the specified attributes
    public void insertChineseQuestion(SQLiteDatabase db, String id, String question, String category, int audio) {
        ContentValues values = new ContentValues();
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry._ID, id);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION, question);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_CATEGORY, category);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO, audio);

        db.insert(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.TABLE_NAME_CHINESE_QUESTION, null, values);
    }

    //inserts a Chinese answer into the DB, with the specified attributes
    public void insertChineseAnswer(SQLiteDatabase db, String id, String answer, int audio) {
        ContentValues values = new ContentValues();
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry._ID, id);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER, answer);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_AUDIO, audio);

        db.insert(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.TABLE_NAME_CHINESE_ANSWER, null, values);
    }

    //  Inserts QuestionXAnswer Relation
    public void insertQuestionXAnswer(SQLiteDatabase db, String id, String QuestionID, String AnswerID) {
        ContentValues values = new ContentValues();
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry._ID, id);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_QUESTION_ID, QuestionID);
        values.put(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.COLUMN_NAME_ANSWER_ID, AnswerID);

        db.insert(com.kcb0126.developer.mibuddy.utils.DataBaseContract.DataBaseEntry.TABLE_NAME_QUESTION_X_ANSWER, null, values);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(com.kcb0126.developer.mibuddy.utils.DataBaseContract.SQL_DELETE_USER);
        db.execSQL(DataBaseContract.SQL_DELETE_ENGLISH_QUESTION);
        db.execSQL(DataBaseContract.SQL_DELETE_ENGLISH_ANSWER);
        db.execSQL(DataBaseContract.SQL_DELETE_CHINESE_ANSWER);
        db.execSQL(DataBaseContract.SQL_DELETE_CHINESE_QUESTION);
        db.execSQL(DataBaseContract.SQL_DELETE_QUESTION_X_ANSWER);

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
