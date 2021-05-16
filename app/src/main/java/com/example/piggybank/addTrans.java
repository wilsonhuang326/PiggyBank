package com.example.piggybank;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class addTrans extends AppCompatActivity{


    GridView mGridView;
    Button mShuru;
    PopupWindow mPopCalc;

    private Button expense_list, income_list;
    private int selectedPosition;
    private CategoryWithNameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_trans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mShuru = (Button) findViewById(R.id.shuru);
         adapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView) findViewById(R.id.category_list_wName);
        mGridView.setAdapter(adapter);
        mShuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                View view = getLayoutInflater().inflate(R.layout.calculator_pop,null);
                mPopCalc = new PopupWindow(view,mShuru.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);

                mPopCalc.showAtLocation(addTrans.this.findViewById(R.id.addingTrans),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                        0,
                        0);
            }
        });

        CategoryWithNameAdapter adapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView)findViewById(R.id.category_list_wName);
        mGridView.setAdapter(adapter);

        expense_list = (Button) findViewById(R.id.expense_category_list);
        income_list = (Button) findViewById(R.id.income_category_list);
        expense_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.readByTypeFromCategoryTable(0);
            }
        });

        income_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.readByTypeFromCategoryTable(1);
            }
        });
        expense_list.callOnClick();
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

//    public void getCategory(int listType) {
//
//    }
}
