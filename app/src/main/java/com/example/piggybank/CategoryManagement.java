package com.example.piggybank;
/*
分类管理页面
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.lang.reflect.Array;

public class CategoryManagement extends AppCompatActivity {
    private ListView mListView;
    private Button addCategoryActivityButton;
    private CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView=(ListView)findViewById(R.id.category_list);
        adapter = new CategoryAdapter(this);
        mListView.setAdapter(adapter);
        addCategoryActivityButton=(Button) findViewById(R.id.go_to_add_category);


//        System.out.println("edit");
//        Intent intent = new Intent(CategoryManagement.this.getApplicationContext(), AddCategory.class);
//        intent.putExtra("action", "update");
//        intent.putExtra("cid", ((Category)mListView.getItemAtPosition(position)).getCid());
//        intent.putExtra("cname", ((Category)mListView.getItemAtPosition(position)).getName());
//        intent.putExtra("cpath", ((Category)mListView.getItemAtPosition(position)).getIconPath());
//        intent.putExtra("ctype", ((Category)mListView.getItemAtPosition(position)).getType());
//        CategoryManagement.this.startActivity(intent);
        addCategoryActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCategory.class);
                intent.putExtra("action",Action.INSERT.toString());
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.readAllFromCategoryTable();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}