package com.example.piggybank;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Calendar;

public class addTrans extends AppCompatActivity {


    GridView mGridView;
    Button amount_Button,date_Button;
    EditText info_Button;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    PopupWindow mPopCalc;
    private Calc mCalc = null;

    private Button expense_list, income_list;
    private int selectedPosition;
    private CategoryWithNameAdapter adapter;
    int mYear, mMonth,mDay ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_trans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //输入金额
        Calc calc = new Calc(this);
        amount_Button = (Button) findViewById(R.id.trans_amount);
        date_Button = (Button) findViewById(R.id.trans_date);
        info_Button = (EditText) findViewById(R.id.trans_info);
        adapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView) findViewById(R.id.category_list_wName);
        mGridView.setAdapter(adapter);
        mCalc = new Calc(this);
        amount_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calc.showAtLocation(addTrans.this.findViewById(R.id.addingTrans),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                        0,
                        0);

            }
        });

        Calendar calendar = Calendar.getInstance();
       mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d("year", String.valueOf(mYear));
        Log.d("month", String.valueOf(mMonth));
        Log.d("day", String.valueOf(mDay));
        String date = mMonth+1 + "/" + mDay + "/" + mYear;
        date_Button.setText(date);

        date_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog dialog = new DatePickerDialog(addTrans.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        mYear,mMonth,mDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                mDay=day;
                mMonth=month;
                mYear=year;
                String date = mMonth+1 + "/" + mDay + "/" + mYear;

                date_Button.setText(date);
            }
        };





        adapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView) findViewById(R.id.category_list_wName);
        mGridView.setAdapter(adapter);

        CategoryWithNameAdapter adapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView)findViewById(R.id.category_list_wName);
        mGridView.setAdapter(adapter);

        expense_list = (Button) findViewById(R.id.expense_category_list);
        income_list = (Button) findViewById(R.id.income_category_list);
        expense_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.readByTypeFromCategoryTable(CategoryType.EXPENSE);
            }
        });

        income_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.readByTypeFromCategoryTable(CategoryType.INCOME);
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


}
