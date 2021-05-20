package com.example.piggybank;
/*
分类数据库
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class CategorySQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Bank.db";
    private static final String TAG = "DatabaseHelper";

    public static String TABLE_NAME = "category_table";
    public static String FIELD_ID = "cid";
    public static String FIELD_NAME = "name";
    public static String FIELD_ICON = "icon";
    public static String FIELD_TYPE = "type";
    Context context;
    String C_TSQL = "CREATE TABLE " + TABLE_NAME + "(" + FIELD_ID + " integer primary key autoincrement, "
            + FIELD_NAME + " text not null," + FIELD_ICON + " text not null," + FIELD_TYPE + " text not null);";




    public CategorySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(C_TSQL);
            db.execSQL("insert into " + CategorySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('bus', 'ic_category_bus.png', 'expense')");
            db.execSQL("insert into " + CategorySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('gas', 'ic_category_gas_pump.png', 'expense')");
            db.execSQL("insert into " + CategorySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('food', 'ic_category_hamburger.png', 'expense')");
            db.execSQL("insert into " + CategorySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('income', 'ic_category_wallet.png', 'income')");

        } catch (SQLException e) {
            Log.e(TAG, "onCreate " + TABLE_NAME + "Error" + e.toString());

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Category> readAllFromCategoryTable(){
         ArrayList<Category> categoryArray=new ArrayList<Category>();
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        Cursor cursor =dbWriter.query(CategorySQLiteHelper.TABLE_NAME,
                new String[]{CategorySQLiteHelper.FIELD_ID, CategorySQLiteHelper.FIELD_NAME, CategorySQLiteHelper.FIELD_ICON, CategorySQLiteHelper.FIELD_TYPE},
                null,null,null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ID));
            String name = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_NAME));
            String icon = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ICON));
            String type = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_TYPE));
            Category c=new Category(id,name, icon, type);
            categoryArray.add(c);
            Log.i("SQLite","result: id="  + id +" name: " + name +"  icon:" + icon+" type:"+type);
        }

        cursor.close();
        return categoryArray;
    }

    public ArrayList<Category> readByTypeFromCategoryTable(CategoryType listType) {
        ArrayList<Category> categoryArray=new ArrayList<Category>();
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        if (listType .equals(CategoryType.EXPENSE)) {
            cursor = dbWriter.query(CategorySQLiteHelper.TABLE_NAME,
                    new String[]{CategorySQLiteHelper.FIELD_ID, CategorySQLiteHelper.FIELD_NAME, CategorySQLiteHelper.FIELD_ICON, CategorySQLiteHelper.FIELD_TYPE},
                    CategorySQLiteHelper.FIELD_TYPE + "=?", new String[]{CategoryType.EXPENSE.toString()}, null, null, null);
        }else if(listType.equals(CategoryType.INCOME)){
            cursor =dbWriter.query(CategorySQLiteHelper.TABLE_NAME,
                    new String[]{CategorySQLiteHelper.FIELD_ID, CategorySQLiteHelper.FIELD_NAME, CategorySQLiteHelper.FIELD_ICON, CategorySQLiteHelper.FIELD_TYPE},
                    CategorySQLiteHelper.FIELD_TYPE+"=?",new String[]{CategoryType.INCOME.toString()},null,null,null);
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ID));
            String name = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_NAME));
            String icon = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ICON));
            String type = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_TYPE));
            Category c = new Category(id, name, icon, type);
            categoryArray.add(c);

            Log.i("HUANG", "result: id=" + id + " name: " + name + "  icon:" + icon + " type:" + type);
        }
        Log.d("ARRAYSIZE", String.valueOf(categoryArray.size()));
        cursor.close();
        return categoryArray;
    }


    public void addToCategoryTable(String path, String type, String name) {
        Log.d("action", "add");
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        dbWriter.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put(CategorySQLiteHelper.FIELD_TYPE, type);
        cv.put(CategorySQLiteHelper.FIELD_NAME, name);
        cv.put(CategorySQLiteHelper.FIELD_ICON, path);
        dbWriter.insert(CategorySQLiteHelper.TABLE_NAME, null, cv);
        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();
        dbWriter.close();

    }

    public void updateCategoryTable(String cid,String path, String type, String name) {
        Log.d("action", "update");

        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();

        dbWriter.beginTransaction();


        ContentValues cv = new ContentValues();
        cv.put(CategorySQLiteHelper.FIELD_TYPE, type);
        cv.put(CategorySQLiteHelper.FIELD_NAME, name);
        cv.put(CategorySQLiteHelper.FIELD_ICON, path);
        dbWriter.update(CategorySQLiteHelper.TABLE_NAME, cv, CategorySQLiteHelper.FIELD_ID+"= ?", new String[]{cid});


        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();
        dbWriter.close();

    }

    public ArrayList<Category> deleteFromDB(int cid){
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        dbWriter.delete(CategorySQLiteHelper.TABLE_NAME,CategorySQLiteHelper.FIELD_ID+"=?",new String[]{String.valueOf(cid)});
        return readAllFromCategoryTable();
    }
}
