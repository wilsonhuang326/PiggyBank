package com.example.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioGroup;

public class AddCategory extends AppCompatActivity {
    private GridView mGridView;
    private RadioGroup mRadioGroup;
    private EditText mEditText;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRadioGroup = (RadioGroup) findViewById(R.id.input_category_type);
        mEditText = (EditText) findViewById(R.id.input_category);
        mGridView = (GridView) findViewById(R.id.icon_list);


        IconAdapter adapter = new IconAdapter(this);
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

    private void createCategory() {
        String path = getIconPath();
        String type = monitoringRadioGrop();
        String name = getCategoryName();
        if (name.isEmpty()) {
            Log.d("error:", "getCategoryName");
        } else {
            addToCategoryTable(path,type,name);
        }


    }

    private String getIconPath() {
        return String.valueOf(mGridView.getAdapter().getItem(selectedPosition));
    }

    private String monitoringRadioGrop() {
        int checked= mRadioGroup.getCheckedRadioButtonId();
        if (checked==R.id.input_expense){
            return "expense";
        }else if (checked==R.id.input_income){
            return "income";
        }
        return null;

    }

    private String getCategoryName() {
        Log.d("category name:", String.valueOf(mEditText.getText()));
        return String.valueOf(mEditText.getText());
    }

    private void addToCategoryTable(String path,String type,String name) {
        /*
        String fileDirPath = "data/data/com.example.piggybank/files";
        String filename = "category_list.txt";

        FileHelper fileHelper = new FileHelper(fileDirPath, filename,CategoryManagement.this);
        int raw = R.raw.category_list;
        try {
            if (!fileHelper.checkFolderExists()) {
                fileHelper.createFolder();
            }
            if (!fileHelper.checkFileExists()) {
                fileHelper.createFile();
            }
            fileHelper.fileAddLineToTheEnd(name+" "+path+" "+type);
            //fileHelper.copyRawFile(raw);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(this, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        dbWriter.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put(CategorySQLiteHelper.FIELD_TYPE,type);
        cv.put(CategorySQLiteHelper.FIELD_NAME, name);
        cv.put(CategorySQLiteHelper.FIELD_ICON, path);
        dbWriter.insert(CategorySQLiteHelper.TABLE_NAME, null, cv);
        dbWriter.setTransactionSuccessful();
        dbWriter.endTransaction();
        dbWriter.close();

    }

}