package com.example.piggybank;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class calc extends AppCompatActivity implements View.OnClickListener {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnBackward, btnPlus, btnSubtract, btnMultiply, btnDivide, btnComp, btnPoint, btnDate;

    TextView text;
    String str = "";
    boolean clr;

    String money;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_pop);


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

        text = (TextView) findViewById(R.id.text);

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
        btnDate.setOnClickListener(this);


        String[] str = "".split("\\s+");
        System.out.println("case 0:" + Arrays.toString(str));

        String[] str1 = "1 + 2".split("\\s+");
        System.out.println("case 1:" + Arrays.toString(str1));


        String[] str2 = "1".split("\\\\s+");
        System.out.println("case 2:" + Arrays.toString(str2));

        String[] str3 = "1 + ".split(" ");
        System.out.println("case 3:" + Arrays.toString(str3));

        String[] str4 = " + 2".split(" ");
        System.out.println("case 4:" + Arrays.toString(str4));

        String[] str5 = "  +  ".split(" ");
        System.out.println("case 5:" + Arrays.toString(str5));
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
                break;
            case R.id.minus:
            case R.id.add:
            case R.id.div:
            case R.id.mult:

                getResult(input, ((Button) v).getText().toString());

                //text.setText(input + " " + ((Button)v).getText() + " ");
                break;
            case R.id.comp:
                money=getResult(input, ((Button) v).getText().toString());
                System.out.println("completed:"+money);
                //text.setText(input + " " + ((Button)v).getText() + " ");
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
                    text.setText(Integer.parseInt(input) + " " + op + " ");
                    strResult = String.valueOf(Integer.parseInt(input));
                } else {
                    text.setText(input + " " + op + " ");
                    strResult = input;
                }

            } else if (str.length == 3) {//两个数
                double result;
                switch (str[1]) {
                    //addition
                    case "+":
                        result = Double.parseDouble(str[0]) + Double.parseDouble(str[2]);
                        if (result % 1 == 0) {//Integer
                            text.setText((int) result + " " + op + " ");
                            strResult= String.valueOf((int) result);
                        } else {//decimal
                            text.setText(result + " " + op + " ");
                            strResult= String.valueOf(result);
                        }
                        break;

                    case "-":
                        result = Double.parseDouble(str[0]) - Double.parseDouble(str[2]);
                        if (result % 1 == 0) {//Integer
                            text.setText((int) result + " " + op + " ");
                            strResult= String.valueOf((int) result);
                        } else {//decimal
                            text.setText(result + " " + op + " ");
                            strResult= String.valueOf(result);
                        }
                        break;


                    case "×":
                        result = Double.parseDouble(str[0]) * Double.parseDouble(str[2]);
                        if (result % 1 == 0) {//Integer
                            text.setText((int) result + " " + op + " ");
                            strResult= String.valueOf((int) result);
                        } else {//decimal
                            text.setText(result + " " + op + " ");
                            strResult= String.valueOf(result);
                        }
                        break;



                    case "÷":
                        result = Double.parseDouble(str[0]) / Double.parseDouble(str[2]);
                        if (result % 1 == 0) {//Integer
                            text.setText((int) result + " " + op + " ");
                            strResult= String.valueOf((int) result);
                        } else {//decimal
                            text.setText(result + " " + op + " ");
                            strResult= String.valueOf(result);
                        }
                        break;

                }
            }


        }
        return strResult;
    }
}
