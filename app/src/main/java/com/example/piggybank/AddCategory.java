package com.example.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioGroup;

public class AddCategory extends AppCompatActivity {
    private GridView mGridView;
    private RadioGroup mRadioGroup;
    private EditText mEditText;
    private int selectedPosition;
    private String action;
    private IconAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRadioGroup = (RadioGroup) findViewById(R.id.input_category_type);
        mEditText = (EditText) findViewById(R.id.input_category);
        mGridView = (GridView) findViewById(R.id.icon_list);


         adapter = new IconAdapter(this);
        mGridView.setAdapter(adapter);
        action = getIntent().getExtras().getString("action");
        Log.e("after", action);
        if (action.equals("update")) {
            setInput();

        }
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.setSelectedPosition(position);
                selectedPosition = position;


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

                createCategory();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setInput(){
        Log.d("cname",getIntent().getExtras().getString("cname"));
        Log.d("cpath",getIntent().getExtras().getString("cpath"));
        Log.d("ctype",getIntent().getExtras().getString("ctype"));

        mEditText.setText(getIntent().getExtras().getString("cname"));
        String path=getIntent().getExtras().getString("cpath");
        selectedPosition = adapter.setSelectedPosition(path);

        String type = getIntent().getExtras().getString("ctype");
        if (type.equals("支出") || type.equals("expense")) {
            mRadioGroup.check(R.id.input_expense);
        } else if (type.equals("收入") || type.equals("income")) {
            mRadioGroup.check(R.id.input_income);
        }
    }
    private void createCategory() {
        String path = getIconPath();
        String type = monitoringRadioGrop();
        String name = getCategoryName();
        if (name.isEmpty()) {
            Log.d("error:", "getCategoryName");
        } else {
            if (action.equals("add")) {
                addToCategoryTable(path, type, name);
            } else if (action.equals("update")) {
                updateCategoryTable(path, type, name);
            }
        }


    }

    private String getIconPath() {
        return String.valueOf(mGridView.getAdapter().getItem(selectedPosition));
    }

    private String monitoringRadioGrop() {
        int checked = mRadioGroup.getCheckedRadioButtonId();
        if (checked == R.id.input_expense) {
            return "支出";
        } else if (checked == R.id.input_income) {
            return "收入";
        }
        return null;

    }

    private String getCategoryName() {
        Log.d("category name:", String.valueOf(mEditText.getText()));
        return String.valueOf(mEditText.getText());
    }

    private void addToCategoryTable(String path, String type, String name) {
        Log.d("action", "add");
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(this, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        dbWriter.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put(CategorySQLiteHelper.FIELD_TYPE, type);
        cv.put(CategorySQLiteHelper.FIELD_NAME, name);
        cv.put(CategorySQLiteHelper.FIELD_ICON, path);
        dbWriter.insert(CategorySQLiteHelper.TABLE_NAME, null, cv);
        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();
        dbWriter.close();

    }

    private void updateCategoryTable(String path, String type, String name) {
        Log.d("action", "update");

        String cid = getIntent().getExtras().getString("cid");
        Log.d("cid", cid);
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(this, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        SQLiteDatabase dbReader = dbHelper.getReadableDatabase();

        dbWriter.beginTransaction();


                ContentValues cv = new ContentValues();
        cv.put(CategorySQLiteHelper.FIELD_TYPE, type);
        cv.put(CategorySQLiteHelper.FIELD_NAME, name);
        cv.put(CategorySQLiteHelper.FIELD_ICON, path);
        dbWriter.update(CategorySQLiteHelper.TABLE_NAME, cv, CategorySQLiteHelper.FIELD_ID+"= ?", new String[]{cid});


        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();
        dbWriter.close();

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