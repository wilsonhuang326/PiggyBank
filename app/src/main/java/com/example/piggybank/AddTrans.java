package com.example.piggybank;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTrans extends AppCompatActivity {


    GridView mGridView;
    Button amount_Button, date_Button;
    EditText info_Button;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    PopupWindow mPopCalc;
    private Calc mCalc = null;

    private Button expense_list, income_list;
    private int selectedPosition, selectedCID;
    private CategoryWithNameAdapter mAdapter;
    int mYear, mMonth, mDay;
    SimpleDateFormat dateFormat;
    Date mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_trans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //输入金额

        amount_Button = (Button) findViewById(R.id.trans_amount);
        date_Button = (Button) findViewById(R.id.trans_date);
        info_Button = (EditText) findViewById(R.id.trans_info);
        mAdapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView) findViewById(R.id.category_list_wName);
        mGridView.setAdapter(mAdapter);
        Log.d("calc amount", amount_Button.getText().toString());
        Calc calc = new Calc(this);
        calc.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.d("calc resut", calc.getResult());
                amount_Button.setText(calc.getResult());
            }
        });
        amount_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calc.showAtLocation(AddTrans.this.findViewById(R.id.addingTrans),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                        0,
                        0);
            }
        });

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mDate = calendar.getTime();
        String ymd = dateFormat.format(mDate);
        Log.d("getTIme", ymd);
        date_Button.setText(ymd);

        date_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialog dialog = new DatePickerDialog(AddTrans.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        mYear, mMonth, mDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);


                mDay = day;
                mMonth = month;
                mYear = year;
                calendar.set(year, month, day);
                mDate = calendar.getTime();
                Log.d("getTIme", String.valueOf(calendar.getTime()));

                String ymd = dateFormat.format(mDate);
                date_Button.setText(ymd);

                Log.d("getDate", ymd);

            }
        };


        expense_list = (Button) findViewById(R.id.expense_category_list);
        income_list = (Button) findViewById(R.id.income_category_list);
        expense_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.readByTypeFromCategoryTable(CategoryType.EXPENSE);

                mAdapter.setSelectedPosition(0);
                selectedPosition = 0;
                selectedCID = (int) mAdapter.getItemId(selectedPosition);

            }
        });

        income_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAdapter.readByTypeFromCategoryTable(CategoryType.INCOME);

                mAdapter.setSelectedPosition(0);
                selectedPosition = 0;
                selectedCID = (int) mAdapter.getItemId(selectedPosition);


            }
        });
        expense_list.callOnClick();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setSelectedPosition(position);
                selectedPosition = position;
                selectedCID = (int) mAdapter.getItemId(position);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.finish_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_done:

                // createCategory();
                createTransactions();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createTransactions() {
        System.out.println("created");

        String ymd = dateFormat.format(mDate);

//        Log.d("citem", String.valueOf(selectedCID));
//        Log.d("date", ymd);
//        Log.d("amount", amount_Button.getText().toString());
//        Log.d("text", info_Button.getText().toString());
        MySQLiteHelper dbHelper = new MySQLiteHelper(this, null, null, 1);
        dbHelper.addToTransactionTable(selectedCID, Double.parseDouble(amount_Button.getText().toString()), ymd, info_Button.getText().toString());
        //        dbHelper.addToTransactionTable(5,5.55,"2021-01-01","notes5");


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            View view = this.getCurrentFocus();
            if (isShouldHideInput(view, motionEvent)) {
                closeKeyboard(view);

            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void closeKeyboard(View view) {

        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }


    private boolean isShouldHideInput(View view, MotionEvent motionEvent) {
        if (view != null && (view instanceof EditText)) {
            int[] l = {0, 0};
            view.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + view.getHeight(), right = left
                    + view.getWidth();
            if (motionEvent.getX() > left && motionEvent.getX() < right
                    && motionEvent.getY() > top && motionEvent.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }


}


