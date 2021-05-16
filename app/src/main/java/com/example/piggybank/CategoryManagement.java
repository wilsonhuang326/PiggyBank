package com.example.piggybank;

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
    Button addCategoryActivityButton;
    CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mListView=(ListView)findViewById(R.id.category_list);
        adapter = new CategoryAdapter(this);
        mListView.setAdapter(adapter);
        addCategoryActivityButton=(Button) findViewById(R.id.go_to_add_category);

        addCategoryActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCategory.class);
                intent.putExtra("action","add");
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.readAllFromCategoryTable();
        adapter.notifyDataSetChanged();

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