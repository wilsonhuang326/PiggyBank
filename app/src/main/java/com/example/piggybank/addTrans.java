package com.example.piggybank;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class addTrans extends AppCompatActivity implements View.OnClickListener{

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,
            btnBackward,btnPlus,btnSubtract,btnMultiply,btnDivide,btnComp,btnPoint,btnDate;

    TextView text;
    String input = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_trans);



        //计算器/金额输入

        btn0 = (Button) findViewById(R.id.num0);
        btn1 = (Button) findViewById(R.id.num1);
        btn2 = (Button) findViewById(R.id.num2);
        btn3 = (Button) findViewById(R.id.num3);
        btn4 = (Button) findViewById(R.id.num4);
        btn5 = (Button) findViewById(R.id.num5);
        btn6 = (Button) findViewById(R.id.num6);
        btn7 = (Button) findViewById(R.id.num7);
        btn8 = (Button) findViewById(R.id.num8);
        btn9 = (Button) findViewById(R.id.num9);
        btnBackward = (Button) findViewById(R.id.chehui);
        btnPlus = (Button) findViewById(R.id.add);
        btnSubtract = (Button) findViewById(R.id.minus);
        btnMultiply = (Button) findViewById(R.id.mult);
        btnDivide = (Button) findViewById(R.id.div);
        btnPoint = (Button) findViewById(R.id.dot);
        btnComp = (Button) findViewById(R.id.comp);
        btnDate = (Button) findViewById(R.id.date);



        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnBackward.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnComp.setOnClickListener(new click());
        btnDate.setOnClickListener(this);
    }


//    EditText input;
//
//    Button chehui;
//
//    Button div;
//
//    Button mult;
//
//    Button add;
//
//    Button sub;
//
//    Button num1;
//
//    Button num2;
//
//    Button num3;
//
//    Button num4;
//
//    Button num5;
//
//    Button num6;
//
//    Button num7;
//
//    Button num8;
//
//    Button num9;
//
//    Button num0;
//
//    Button dot;
//
//    Button date;


}
