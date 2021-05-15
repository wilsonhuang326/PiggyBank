package com.example.piggybank;

import android.content.Intent;
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

public class addTrans extends AppCompatActivity{
    //
//    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,
//            btnBackward,btnPlus,btnSubtract,btnMultiply,btnDivide,btnComp,btnPoint,btnDate;
//
//    TextView text;
//    String str = "";
//    boolean clr;
    GridView mGridView;

    private int selectedPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_trans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button shuru = (Button) findViewById(R.id.shuru);
        CategoryWithNameAdapter adapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView)findViewById(R.id.category_list_wName);
        mGridView.setAdapter(adapter);
        shuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), calc.class);
                startActivity(intent);
            }
        });
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
