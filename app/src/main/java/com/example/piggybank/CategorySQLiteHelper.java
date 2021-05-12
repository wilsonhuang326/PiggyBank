package com.example.piggybank;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CategorySQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Bank.db";
    private static final String TAG = "DatabaseHelper";

    public static String TABLE_NAME = "category_table";
    public static String FIELD_ID = "cid";
    public static String FIELD_NAME = "name";
    public static String FIELD_ICON = "icon";
    public static String FIELD_TYPE = "type";

    String C_TSQL = "CREATE TABLE " + TABLE_NAME + "(" + FIELD_ID + " integer primary key autoincrement, "
            + FIELD_NAME + " text not null," + FIELD_ICON + " text not null," + FIELD_TYPE + " text not null);";




    public CategorySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(C_TSQL);
            db.execSQL("insert into " + CategorySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('name1', 'icon4', 'type1')");
            db.execSQL("insert into " + CategorySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('name2', 'icon3', 'type2')");
            db.execSQL("insert into " + CategorySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('name3', 'icon2', 'type3')");
            db.execSQL("insert into " + CategorySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('name4', 'icon1', 'type4')");

        } catch (SQLException e) {
            Log.e(TAG, "onCreate " + TABLE_NAME + "Error" + e.toString());

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
