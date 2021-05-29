package com.example.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

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
        if (Action.UPDATE.equalsType(action)) {
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
                if (getCategoryName().isEmpty()) {
                    Toast.makeText(AddCategory.this, "请输入分类名。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (Action.UPDATE.equalsType(action)) {
                        updateCategory();
                    } else if (Action.INSERT.equalsType(action)) {
                        createCategory();

                    }
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setInput() {
        mEditText.setText(getIntent().getExtras().getString("cname"));
        String path = getIntent().getExtras().getString("cpath");
        selectedPosition = adapter.setSelectedPosition(path);

        String type = getIntent().getExtras().getString("ctype");
        if (type.equals("支出") || type.equals("expense")) {
            mRadioGroup.check(R.id.input_expense);
        } else if (type.equals("收入") || type.equals("income")) {
            mRadioGroup.check(R.id.input_income);
        }
    }

    private void updateCategory() {
        new AlertDialog.Builder(this)
                .setMessage("确定要修改这个分类吗")
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createCategory();
                        Toast.makeText(AddCategory.this, "修改完成。",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("否", null)
                .show();
    }

    private void createCategory() {
        String path = getIconPath();
        String type = monitoringRadioGrop();
        String name = getCategoryName();
        if (name.isEmpty()) {
            Log.d("error:", "getCategoryName");
        } else {
            MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this, null, null, 1);
            if (Action.INSERT.equalsType(action)) {
                // addToCategoryTable(path, type, name);
                mySQLiteHelper.addToCategoryTable(path, type, name);
            } else if (Action.UPDATE.equalsType(action)) {
                String cid = getIntent().getExtras().getString("cid");
                mySQLiteHelper.updateCategoryTable(cid, path, type, name);
            }
        }

        finish();

    }

    private String getIconPath() {
        return String.valueOf(mGridView.getAdapter().getItem(selectedPosition));
    }

    private String monitoringRadioGrop() {
        int checked = mRadioGroup.getCheckedRadioButtonId();
        if (checked == R.id.input_expense) {
            return CategoryType.EXPENSE.toString();
        } else if (checked == R.id.input_income) {
            return CategoryType.INCOME.toString();
        }
        return null;

    }

    private String getCategoryName() {
        return String.valueOf(mEditText.getText());
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