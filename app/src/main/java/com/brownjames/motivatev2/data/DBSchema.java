package com.brownjames.motivatev2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSchema extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "motivate.db";

    //Tables
    public static final String TABLE_TASKS  = "tasks";

    private static final String COMMA_SPACE     = ", ";
    private static final String CREATE_TABLE    = "CREATE TABLE ";
    private static final String PRIMARY_KEY     = "PRIMARY KEY ";
    private static final String UNIQUE          = "UNIQUE ";
    private static final String TYPE_TEXT       = " TEXT ";
    private static final String TYPE_INT        = " INTEGER ";
    private static final String TYPE_REAL       = " REAL ";
    private static final String DEFAULT         = "DEFAULT ";
    private static final String AUTOINCREMENT   = "AUTOINCREMENT ";
    private static final String NOT_NULL        = "NOT NULL ";
    private static final String DROP_TABLE      = "DROP TABLE IF EXISTS ";

    public static final class TB_TASKS {
        public static final String ID = "_id";
        public static final String TITLE = "title";
        public static final String COMPLETE_BY = "complete_by";
        public static final String CURRENCY_VALUE = "currency_value";
        public static final String CURRENCY_TYPE = "currency_type";             // £, $ etc..
    }

    private static final String CREATE_TABLE_TASKS =
            CREATE_TABLE + TABLE_TASKS + " ( " +
                    TB_TASKS.ID + TYPE_INT + NOT_NULL + PRIMARY_KEY + AUTOINCREMENT + UNIQUE + COMMA_SPACE +
                    TB_TASKS.TITLE + TYPE_TEXT + NOT_NULL + COMMA_SPACE +
                    TB_TASKS.COMPLETE_BY + TYPE_INT + NOT_NULL + COMMA_SPACE +
                    TB_TASKS.CURRENCY_VALUE + TYPE_REAL + NOT_NULL + COMMA_SPACE +
                    TB_TASKS.CURRENCY_TYPE + TYPE_TEXT + NOT_NULL  +
                    " );";


    public DBSchema(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TASKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_TABLE);
    }
}
