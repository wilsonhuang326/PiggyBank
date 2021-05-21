package com.example.piggybank;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Calendar;

public class Calc extends PopupWindow implements View.OnClickListener {

    //计算器
    private View view;
    Button btn0, btn1, btn2, btn3,
  btn4, btn5, btn6, btn7, btn8, btn9,
            btnBackward, btnPlus, btnSubtract, btnMultiply, btnDivide, btnComp, btnPoint;



    TextView text;
    String str = "";
    boolean clr;
    String money;

    //日期
    private TextView mDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;




    public Calc(Activity context) {


        super(context);

        view =View.inflate(context,R.layout.calculator_pop,null);
        setContentView(view);
        this.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);


//     private View mView;
//     String money;
//     public Calc (Activity context){
//         super(context);
//         LayoutInflater mInflater = (LayoutInflater) context
//  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//         mView = mInflater.inflate(R.layout.calculator_pop, null);

//         this.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
//         setContentView(mView);
  
//        setTouchable(true);
//     setOutsideTouchable(true);
//        setFocusable(true);
//        setBackgroundDrawable(new BitmapDrawable(context.getResources()));
        setOutsideTouchable(true);
        btn0 = (Button) view.findViewById(R.id.num0);
        btn1 = (Button) view.findViewById(R.id.num1);
        btn2 = (Button) view.findViewById(R.id.num2);
        btn3 = (Button) view.findViewById(R.id.num3);
        btn4 = (Button) view.findViewById(R.id.num4);
        btn5 = (Button) view.findViewById(R.id.num5);
        btn6 = (Button) view.findViewById(R.id.num6);
        btn7 = (Button) view.findViewById(R.id.num7);
        btn8 = (Button) view.findViewById(R.id.num8);
        btn9 = (Button) view.findViewById(R.id.num9);
        btnBackward = (Button) view.findViewById(R.id.chehui);
        btnPlus = (Button) view.findViewById(R.id.add);
        btnSubtract = (Button) view.findViewById(R.id.minus);
        btnMultiply = (Button) view.findViewById(R.id.mult);
        btnDivide = (Button) view.findViewById(R.id.div);
        btnPoint = (Button) view.findViewById(R.id.dot);
        btnComp = (Button) view.findViewById(R.id.comp);


        text = (TextView) view.findViewById(R.id.text);


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
        btnComp.setOnClickListener(this);

        //日期
        mDate = view.findViewById(R.id.date1);
        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(context,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }

        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                //Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDate.setText(date);
            }
        };


    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


//    public static boolean isNumeric(String str){
//        for (int i = str.length();--i>=0;){ if (!Character.isDigit(str.charAt(i))){ return false; }
//        }
//        return true;
//    }


    public void onClick(View v) {
        String input = text.getText().toString();

        switch (v.getId()) {
            case R.id.num0:
            case R.id.num1:
            case R.id.num2:
            case R.id.num3:
            case R.id.num4:
            case R.id.num5:
            case R.id.num6:
            case R.id.num7:
            case R.id.num8:
            case R.id.num9:
            case R.id.dot:
                text.setText(input + ((Button) v).getText());
                String[] str = text.getText().toString().split(" ");
                Log.d("str length", String.valueOf(str.length));
                if (str.length==1){
                money=text.getText().toString();}else {
                    money=str[0];
                }
                break;
            case R.id.minus:
            case R.id.add:
            case R.id.div:
            case R.id.mult:

                money=getResult(input, " "+((Button) v).getText().toString()+" ");

                //text.setText(input + " " + ((Button)v).getText() + " ");
                break;
            case R.id.comp:
               money=getResult(input, "");
               if (money.isEmpty()){
                   money="0";
               }
                if (Double.parseDouble(money)%1!=0){
                    money =String.valueOf((double)Math.round(Double.parseDouble(money) * 100) / 100);
                }
                System.out.println("completed:"+money);
                //text.setText(input + " " + ((Button)v).getText() + " ");
                dismiss();

                break;
            case R.id.chehui:
                text.setText("");
                break;

        }
    }


    private String getResult(String input, String op) {


        String strResult="";

        if (!input.isEmpty()) {
            String[] str = input.split(" ");

            if (str.length == 1) {//一个数
                if (Double.parseDouble(input) % 1 == 0) {
                    text.setText(Integer.parseInt(input) + op);
                    strResult = String.valueOf(Integer.parseInt(input));
                } else {
                    text.setText(input + op);
                    strResult = input;
                }

            } else if (str.length==2){//one number one operator
                double result = Double.parseDouble(str[0]);
                if (result % 1 == 0) {//Integer
                    text.setText((int) result + op);
                    strResult= String.valueOf((int) result);
                } else {//decimal
                    text.setText(result + op);
                    strResult= String.valueOf(result);
                }
            }
                else if (str.length == 3) {//两个数
                double result;
                switch (str[1]) {
                    //addition
                    case "+":
                        result = Double.parseDouble(str[0]) + Double.parseDouble(str[2]);
                        if (result % 1 == 0) {//Integer
                            text.setText((int) result + op);
                            strResult= String.valueOf((int) result);
                        } else {//decimal
                            text.setText(result + op);
                            strResult= String.valueOf(result);
                        }
                        break;

                    case "-":
                        result = Double.parseDouble(str[0]) - Double.parseDouble(str[2]);
                        if (result % 1 == 0) {//Integer
                            text.setText((int) result + op);
                            strResult= String.valueOf((int) result);
                        } else {//decimal
                            text.setText(result + op);
                            strResult= String.valueOf(result);
                        }
                        break;


                    case "×":
                        result = Double.parseDouble(str[0]) * Double.parseDouble(str[2]);
                        if (result % 1 == 0) {//Integer
                            text.setText((int) result + op);
                            strResult= String.valueOf((int) result);
                        } else {//decimal
                            text.setText(result + op);
                            strResult= String.valueOf(result);
                        }
                        break;



                    case "÷":
                        result = Double.parseDouble(str[0]) / Double.parseDouble(str[2]);
                        if (result % 1 == 0) {//Integer
                            text.setText((int) result + op);
                            strResult= String.valueOf((int) result);
                        } else {//decimal
                            text.setText(result + op);
                            strResult= String.valueOf(result);
                        }
                        break;

                }
            }


        }
        return strResult;
    }

    public String getResult(){
        return money;
    }


}
