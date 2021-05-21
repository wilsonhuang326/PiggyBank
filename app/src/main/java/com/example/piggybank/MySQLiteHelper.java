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

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PiggyBank.db";
    private static final String TAG = "DatabaseHelper";

    public static String CATEGORY_TABLE = "category_table";
    public static String FIELD_CID = "cid";
    public static String FIELD_NAME = "name";
    public static String FIELD_ICON = "icon";
    public static String FIELD_TYPE = "type";
    Context context;

    public static String TRANSACTION_TABLE = "transaction_table";
    public static String FIELD_TID = "tid";
//    public static String FIELD_CID = "cid";
    public static String FIELD_AMOUNT = "amount";
    public static String FIELD_DATE = "date";
    public static String FIELD_TEXT = "text";


    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {//category table
            String C_TSQL = "CREATE TABLE " + CATEGORY_TABLE + "(" + FIELD_CID + " integer primary key autoincrement, "
                    + FIELD_NAME + " text not null," + FIELD_ICON + " text not null," + FIELD_TYPE + " text not null);";
            db.execSQL(C_TSQL);
            db.execSQL("insert into " + MySQLiteHelper.CATEGORY_TABLE + " ( name, icon, type) values ('bus', 'ic_category_bus.png', 'expense')");
            db.execSQL("insert into " + MySQLiteHelper.CATEGORY_TABLE + " ( name, icon, type) values ('gas', 'ic_category_gas_pump.png', 'expense')");
            db.execSQL("insert into " + MySQLiteHelper.CATEGORY_TABLE + " ( name, icon, type) values ('food', 'ic_category_hamburger.png', 'expense')");
            db.execSQL("insert into " + MySQLiteHelper.CATEGORY_TABLE + " ( name, icon, type) values ('income', 'ic_category_wallet.png', 'income')");

        } catch (SQLException e) {
            Log.e(TAG, "onCreate " + CATEGORY_TABLE + "Error" + e.toString());

        }


        try {//transaction table
            String T_TSQL = "CREATE TABLE " + TRANSACTION_TABLE + "("
                    + FIELD_TID + " integer primary key autoincrement, "
                    + FIELD_CID + " integer references category_table(cid) on update cascade,"
                    + FIELD_AMOUNT + " real not null,"
                    + FIELD_DATE + " text not null,"
                    + FIELD_TEXT + " text not null);";
            db.execSQL(T_TSQL);

        } catch (SQLException e) {
            Log.e(TAG, "onCreate " + TRANSACTION_TABLE + "Error" + e.toString());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);//open foreign key
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }

    }

    public ArrayList<Category> readAllFromCategoryTable(){
         ArrayList<Category> categoryArray=new ArrayList<Category>();
        SQLiteOpenHelper dbHelper = new MySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        Cursor cursor =dbWriter.query(MySQLiteHelper.CATEGORY_TABLE,
                new String[]{MySQLiteHelper.FIELD_CID, MySQLiteHelper.FIELD_NAME, MySQLiteHelper.FIELD_ICON, MySQLiteHelper.FIELD_TYPE},
                null,null,null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.FIELD_CID));
            String name = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.FIELD_NAME));
            String icon = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.FIELD_ICON));
            String type = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.FIELD_TYPE));
            Category c=new Category(id,name, icon, type);
            categoryArray.add(c);
            Log.i("SQLite","result: id="  + id +" name: " + name +"  icon:" + icon+" type:"+type);
        }

        cursor.close();
        return categoryArray;
    }

    public ArrayList<Category> readByTypeFromCategoryTable(CategoryType listType) {
        ArrayList<Category> categoryArray=new ArrayList<Category>();
        SQLiteOpenHelper dbHelper = new MySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        if (listType .equals(CategoryType.EXPENSE)) {
            cursor = dbWriter.query(MySQLiteHelper.CATEGORY_TABLE,
                    new String[]{MySQLiteHelper.FIELD_CID, MySQLiteHelper.FIELD_NAME, MySQLiteHelper.FIELD_ICON, MySQLiteHelper.FIELD_TYPE},
                    MySQLiteHelper.FIELD_TYPE + "=?", new String[]{CategoryType.EXPENSE.toString()}, null, null, null);
        }else if(listType.equals(CategoryType.INCOME)){
            cursor =dbWriter.query(MySQLiteHelper.CATEGORY_TABLE,
                    new String[]{MySQLiteHelper.FIELD_CID, MySQLiteHelper.FIELD_NAME, MySQLiteHelper.FIELD_ICON, MySQLiteHelper.FIELD_TYPE},
                    MySQLiteHelper.FIELD_TYPE+"=?",new String[]{CategoryType.INCOME.toString()},null,null,null);
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.FIELD_CID));
            String name = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.FIELD_NAME));
            String icon = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.FIELD_ICON));
            String type = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.FIELD_TYPE));
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
        SQLiteOpenHelper dbHelper = new MySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        dbWriter.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.FIELD_TYPE, type);
        cv.put(MySQLiteHelper.FIELD_NAME, name);
        cv.put(MySQLiteHelper.FIELD_ICON, path);
        dbWriter.insert(MySQLiteHelper.CATEGORY_TABLE, null, cv);
        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();
        dbWriter.close();

    }

    public void updateCategoryTable(String cid,String path, String type, String name) {
        Log.d("action", "update");

        SQLiteOpenHelper dbHelper = new MySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();

        dbWriter.beginTransaction();


        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.FIELD_TYPE, type);
        cv.put(MySQLiteHelper.FIELD_NAME, name);
        cv.put(MySQLiteHelper.FIELD_ICON, path);
        dbWriter.update(MySQLiteHelper.CATEGORY_TABLE, cv, MySQLiteHelper.FIELD_CID+"= ?", new String[]{cid});


        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();
        dbWriter.close();

    }

    public ArrayList<Category> deleteFromCategoryTable(int cid){
        SQLiteOpenHelper dbHelper = new MySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        dbWriter.delete(MySQLiteHelper.CATEGORY_TABLE, MySQLiteHelper.FIELD_CID+"=?",new String[]{String.valueOf(cid)});
        return readAllFromCategoryTable();
    }




    public void addToTransactionTable(int cid, double amount, String date,String text) {
        System.out.println("Transaction:"+cid+","+amount+","+date+","+text);
        SQLiteOpenHelper dbHelper = new MySQLiteHelper(context, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        dbWriter.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put(MySQLiteHelper.FIELD_CID, cid);
        cv.put(MySQLiteHelper.FIELD_AMOUNT, amount);
        cv.put(MySQLiteHelper.FIELD_DATE, date);
        cv.put(MySQLiteHelper.FIELD_TEXT, text);

        dbWriter.insert(MySQLiteHelper.TRANSACTION_TABLE, null, cv);
        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();
        dbWriter.close();

    }
//    MySQLiteHelper dbHelper = new MySQLiteHelper(this, null, null, 1);
//        dbHelper.addToTransactionTable(1,1.1,"2021-01-01","notes1");
//        dbHelper.addToTransactionTable(2,2.2,"2021-01-01","notes2");
//
//        dbHelper.addToTransactionTable(5,5.55,"2021-01-01","notes5");
}
