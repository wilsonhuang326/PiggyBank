package com.example.piggybank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;

public class CategoryManagement extends AppCompatActivity {
    private GridView mGridView;
    private RadioGroup mRadioGroup;
    private EditText mEditText;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRadioGroup = (RadioGroup) findViewById(R.id.input_category_type);
        mEditText = (EditText) findViewById(R.id.input_category);
        mGridView = (GridView) findViewById(R.id.category_list);


        CategoryAdapter adapter = new CategoryAdapter(this);
        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("before", String.valueOf(adapter.selectedPosition));

                adapter.setSelectedPosition(position);
                selectedPosition = position;

                Log.e("after", String.valueOf(adapter.selectedPosition));


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.category_management_actionbar, menu);
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
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createCategory() {
        getIconPath();
        monitoringRadioGrop();
        String s=getCategoryName();
        if (s.isEmpty()) {
            Log.d("error:", "getCategoryName");
        } else {
            Log.d("Created:", "haha");

        }


    }

    private void getIconPath() {
        Log.d("selecteditemposition:", String.valueOf(selectedPosition));
        Log.d("selecteditem:", String.valueOf(mGridView.getAdapter().getItem(selectedPosition)));
    }

    private void monitoringRadioGrop() {

        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.input_outcome:

                Log.d("category type:", "outcome");
                break;
            case R.id.input_income:

                Log.d("category type:", "income");
                break;

            default:
                Log.d("category type:", "error");
                break;
        }

    }

    private String getCategoryName() {
        Log.d("category name:", String.valueOf(mEditText.getText()));
        return String.valueOf(mEditText.getText());
    }
}