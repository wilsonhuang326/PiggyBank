package com.example.piggybank;

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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class addTrans extends AppCompatActivity {
    //
//    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,
//            btnBackward,btnPlus,btnSubtract,btnMultiply,btnDivide,btnComp,btnPoint,btnDate;
//
//    TextView text;
//    String str = "";
//    boolean clr;
    private GridView mGridView;
    private Button expense_list, income_list;
    private int selectedPosition;
    private CategoryWithNameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_trans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button shuru = (Button) findViewById(R.id.shuru);
         adapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView) findViewById(R.id.category_list_wName);
        mGridView.setAdapter(adapter);
        shuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), calc.class);
                startActivity(intent);
            }
        });
        expense_list = (Button) findViewById(R.id.expense_category_list);
        income_list = (Button) findViewById(R.id.income_category_list);
        expense_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCategory(0);
            }
        });

        income_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCategory(1);
            }
        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedPosition(position);
                selectedPosition = position;
            }
        });


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

    public void getCategory(int listType) {
        adapter.readByTypeFromCategoryTable(listType);
    }
}
