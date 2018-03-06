package com.kcb0126.developer.mibuddy.utils;

import android.provider.BaseColumns;

/**
 * Created by developer on 3/6/2018.
 */

public class DataBaseContract {

    private DataBaseContract() {
    }

    //Table names and atyributes
    public static class DataBaseEntry implements BaseColumns {

        //repeated in some tables
        public static final String COLUMN_NAME_QUESTION = "question";
        public static final String COLUMN_NAME_ANSWER = "answer";
        public static final String COLUMN_NAME_AUDIO = "audio";
        public static final String COLUMN_NAME_CATEGORY = "category";

        // Table USER
        public static final String TABLE_NAME_USER = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";

        //Table ENGLISHQUESTION
        public static final String TABLE_NAME_ENGLISH_QUESTION = "englishquestion";

        //Table CHINESEQUESTION
        public static final String TABLE_NAME_CHINESE_QUESTION = "chinesequestion";

        //Table ENGLISHANSWER
        public static final String TABLE_NAME_ENGLISH_ANSWER = "englishanswer";

        //Table CHINESEANSWER
        public static final String TABLE_NAME_CHINESE_ANSWER = "chineseanswer";

        //Table ENGLISHQUESTIONxCHINESEANSWER
        public static final String TABLE_NAME_QUESTION_X_ANSWER = "questionxanswer";
        public static final String COLUMN_NAME_QUESTION_ID = "questionid";
        public static final String COLUMN_NAME_ANSWER_ID = "answerid";

    }

    //Types and separators
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String REAL_TYPE = " REAL";

    //creates table USER
    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_USER + " (" +
                    DataBaseEntry._ID + TEXT_TYPE + " PRIMARY KEY NOT NULL," +
                    DataBaseEntry.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + ")";

    //deletes table USER
    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_USER;


    //creates table ENGLISHQUESTION
    public static final String SQL_CREATE_ENGLISH_QUESTION =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ENGLISH_QUESTION + " (" +
                    DataBaseEntry._ID + TEXT_TYPE + " PRIMARY KEY NOT NULL," +
                    DataBaseEntry.COLUMN_NAME_QUESTION + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_CATEGORY + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_AUDIO + INTEGER_TYPE + ")";

    //deletes table ENGLISHQUESTION
    public static final String SQL_DELETE_ENGLISH_QUESTION =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ENGLISH_QUESTION;


    //creates table CHINESEQUESTIONS
    public static final String SQL_CREATE_CHINESE_QUESTION =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_CHINESE_QUESTION + " (" +
                    DataBaseEntry._ID + TEXT_TYPE + " PRIMARY KEY NOT NULL," +
                    DataBaseEntry.COLUMN_NAME_QUESTION + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_CATEGORY + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_AUDIO + INTEGER_TYPE + ")";

    //deletes table CHiNESE QUESTION
    public static final String SQL_DELETE_CHINESE_QUESTION =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_CHINESE_QUESTION;

    //creates table ENGLISHANSWER
    public static final String SQL_CREATE_ENGLISH_ANSWER =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ENGLISH_ANSWER + " (" +
                    DataBaseEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    DataBaseEntry.COLUMN_NAME_ANSWER + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_AUDIO + INTEGER_TYPE + ")";

    //deletes table ENGLISHANSWER
    public static final String SQL_DELETE_ENGLISH_ANSWER =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ENGLISH_ANSWER;

    //creates table CHINESEANSWER
    public static final String SQL_CREATE_CHINESE_ANSWER =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_CHINESE_ANSWER + " (" +
                    DataBaseEntry._ID + TEXT_TYPE + " PRIMARY KEY NOT NULL," +
                    DataBaseEntry.COLUMN_NAME_ANSWER + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_AUDIO + INTEGER_TYPE + ")";

    //deletes table CHINESEANSWER
    public static final String SQL_DELETE_CHINESE_ANSWER =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_CHINESE_ANSWER;


    //creates table QUESTION_X_ANSWER
    public static final String SQL_CREATE_QUESTION_X_ANSWER =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_QUESTION_X_ANSWER + " (" +
                    DataBaseEntry._ID + TEXT_TYPE + " PRIMARY KEY NOT NULL," +
                    DataBaseEntry.COLUMN_NAME_QUESTION_ID + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ANSWER_ID + TEXT_TYPE + " NOT NULL " + ")";

    //deletes table QUESTION_X_ANSWER
    public static final String SQL_DELETE_QUESTION_X_ANSWER =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_QUESTION_X_ANSWER;


}
