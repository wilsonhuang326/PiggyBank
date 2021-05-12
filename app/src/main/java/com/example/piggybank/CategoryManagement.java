package com.example.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

public class CategoryManagement extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);
        readAllFromCategoryTable();
        mListView=(ListView)findViewById(R.id.category_list);
        CategoryAdapter adapter = new CategoryAdapter(this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("mListView");

            }
        });

    }
    private void readAllFromCategoryTable(){
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(this, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        Cursor cursor =dbWriter.query(CategorySQLiteHelper.TABLE_NAME,
                new String[]{CategorySQLiteHelper.FIELD_ID, CategorySQLiteHelper.FIELD_NAME, CategorySQLiteHelper.FIELD_ICON, CategorySQLiteHelper.FIELD_TYPE},
                null,null,null,null,null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ID));
            String name = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_NAME));
            String icon = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ICON));
            String type = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_TYPE));

            Log.i("Mainactivity","result: id="  + id +" name: " + name +"  icon:" + icon+" type:"+type);
        }

        cursor.close();
    }
}