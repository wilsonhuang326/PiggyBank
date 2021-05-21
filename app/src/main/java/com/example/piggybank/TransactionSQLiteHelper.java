//package com.example.piggybank;
///*
//分类数据库
// */
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.util.ArrayList;
//
//public class TransactionSQLiteHelper extends SQLiteOpenHelper {
//
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "Transaction.db";
//    private static final String TAG = "DatabaseHelper";
//
//    public static String TABLE_NAME = "transaction_table";
//    public static String FIELD_ID = "tid";
//    public static String FIELD_CID = "cid";
//    public static String FIELD_AMOUNT = "amount";
//    public static String FIELD_DATE = "date";
//    public static String FIELD_TEXT = "text";
//    Context context;
//
//
//    public TransactionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
//        this.context = context;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        try {
//            String T_TSQL = "CREATE TABLE " + TABLE_NAME + "("
//                    + FIELD_ID + " integer primary key autoincrement, "
//                    + FIELD_CID + " integer references category_table(cid) on update cascade,"
//                    + FIELD_AMOUNT + " real not null,"
//                    + FIELD_DATE + " text not null,"
//                    + FIELD_TEXT + " text not null);";
//            db.execSQL(T_TSQL);
//            db.execSQL("insert into " + TransactionSQLiteHelper.TABLE_NAME + " ( cid, amount,date,text) " +
//                    "values ('1', '10.1', '2000-02-15','notes')");
//
//        } catch (SQLException e) {
//            Log.e(TAG, "onCreate " + TABLE_NAME + "Error" + e.toString());
//
//        }
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    @Override
//    public void onOpen(SQLiteDatabase db) {
//        super.onOpen(db);//open foreign key
//        if (!db.isReadOnly()) {
//            db.execSQL("PRAGMA foreign_keys=ON;");
//        }
//
//    }
//
//    public void addToTransactionTable(int cid, double amount, String date,String text) {
//        Log.d("action", "add");
//        SQLiteOpenHelper dbHelper = new TransactionSQLiteHelper(context, null, null, 1);
//        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
//        dbWriter.beginTransaction();
//        ContentValues cv = new ContentValues();
//        cv.put(TransactionSQLiteHelper.FIELD_CID, cid);
//        cv.put(TransactionSQLiteHelper.FIELD_AMOUNT, amount);
//        cv.put(TransactionSQLiteHelper.FIELD_DATE, date);
//        cv.put(TransactionSQLiteHelper.FIELD_TEXT, text);
//
//        dbWriter.insert(TransactionSQLiteHelper.TABLE_NAME, null, cv);
//        dbWriter.setTransactionSuccessful();
//        dbWriter.endTransaction();
//        dbWriter.close();
//
//    }
//}
