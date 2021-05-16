package com.example.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

    private int selectedPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_trans);





        mShuru = (Button) findViewById(R.id.shuru);
        mShuru.setOnClickListener(new View.OnClickListener() {

            calc calculator = new calc();
            @Override
            public void onClick(View v) {



                View view = getLayoutInflater().inflate(R.layout.calculator_pop,null);
                mPopCalc = new PopupWindow(view,mShuru.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);


                calculator.startCalc();


                mPopCalc.showAtLocation(addTrans.this.findViewById(R.id.addingTrans),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                        0,
                        0);
            }
        });



        CategoryWithNameAdapter adapter = new CategoryWithNameAdapter(this);
        mGridView = (GridView)findViewById(R.id.category_list_wName);
        mGridView.setAdapter(adapter);
//        mShuru.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), calc.class);
//                startActivity(intent);
//            }
//        });
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



}





