package com.example.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();


        /*
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        ImageButton imageButton=(ImageButton)findViewById(R.id.imageButton);


*/
        /*
      SQLiteOpenHelper dbHelper = new MySQLiteHelper(this, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        //SQLiteDatabase dbReader = dbHelper.getReadableDatabase();

        dbWriter.beginTransaction();


//insert method 1:
       dbWriter.execSQL("insert into " + MySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('name', 'icon', 'type')");
       // insert method 2

     ContentValues cv = new ContentValues();
         cv.put(MySQLiteHelper.FIELD_TYPE,"income");
        cv.put(MySQLiteHelper.FIELD_NAME, "bus");
        cv.put(MySQLiteHelper.FIELD_ICON,"bus.png");
       dbWriter.insert(MySQLiteHelper.TABLE_NAME, null, cv);


        //or
            //dbWriter.insertOrThrow(MySQLiteHelper.TABLE_NAME, null, cv);




        dbWriter.execSQL("insert into " + MySQLiteHelper.TABLE_NAME + " ( name, icon, type) values ('name', 'icon', 'type')");


        //delete
     //   dbWriter.execSQL("DELETE FROM "+ MySQLiteHelper.TABLE_NAME+" WHERE cid=1");
       // dbWriter.delete(MySQLiteHelper.TABLE_NAME,"cid = 3",null);







        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();


        dbWriter.close();
        //dbReader.close();
        */
    }


}