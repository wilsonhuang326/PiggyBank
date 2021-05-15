package com.example.piggybank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        //计算器/金额输入

//        btn0 = (Button) findViewById(R.id.num0);
//        btn1 = (Button) findViewById(R.id.num1);
//        btn2 = (Button) findViewById(R.id.num2);
//        btn3 = (Button) findViewById(R.id.num3);
//        btn4 = (Button) findViewById(R.id.num4);
//        btn5 = (Button) findViewById(R.id.num5);
//        btn6 = (Button) findViewById(R.id.num6);
//        btn7 = (Button) findViewById(R.id.num7);
//        btn8 = (Button) findViewById(R.id.num8);
//        btn9 = (Button) findViewById(R.id.num9);
//        btnBackward = (Button) findViewById(R.id.chehui);
//        btnPlus = (Button) findViewById(R.id.add);
//        btnSubtract = (Button) findViewById(R.id.minus);
//        btnMultiply = (Button) findViewById(R.id.mult);
//        btnDivide = (Button) findViewById(R.id.div);
//        btnPoint = (Button) findViewById(R.id.dot);
//        btnComp = (Button) findViewById(R.id.comp);
//        btnDate = (Button) findViewById(R.id.date);
//
//        text = (TextView) findViewById(R.id.text) ;
//
//        btn0.setOnClickListener(this);
//        btn1.setOnClickListener(this);
//        btn2.setOnClickListener(this);
//        btn3.setOnClickListener(this);
//        btn4.setOnClickListener(this);
//        btn5.setOnClickListener(this);
//        btn6.setOnClickListener(this);
//        btn7.setOnClickListener(this);
//        btn8.setOnClickListener(this);
//        btn9.setOnClickListener(this);
//        btnBackward.setOnClickListener(this);
//        btnPlus.setOnClickListener(this);
//        btnSubtract.setOnClickListener(this);
//        btnMultiply.setOnClickListener(this);
//        btnDivide.setOnClickListener(this);
//        btnPoint.setOnClickListener(this);
//        btnComp.setOnClickListener(new click());
//        btnDate.setOnClickListener(this);
//
//
//        }
//        class click implements View.OnClickListener{
//            public void onClick(View v) {
//                getResult();
//
//            }
//        }
//
//        public void onClick(View v){
//            String input = text.getText().toString();
//            switch (v.getId()){
//                case R.id.num0:
//                case R.id.num1:
//                case R.id.num2:
//                case R.id.num3:
//                case R.id.num4:
//                case R.id.num5:
//                case R.id.num6:
//                case R.id.num7:
//                case R.id.num8:
//                case R.id.num9:
//                case R.id.dot:
////                    if (clr == true){
////                        clr = false;
////                        str = "";
////                        text.setText("");
////                    }
//                    text.setText(input + ((Button)v).getText());
//                    break;
//                case R.id.minus:
//                case R.id.add:
//                case R.id.div:
//                case R.id.mult:
////                    if (clr == true){
////                        clr = false;
////                        str = "";
////                        text.setText("");
////                    }
//                    text.setText(input + " " + ((Button)v).getText() + " ");
//                    break;
//                case R.id.chehui:
//                    text.setText("");
//                    break;
//            }
//        }
//
//        private void getResult(){
//
//
//            //text.setText("111");
//
//            String s = text.getText().toString();
//            double result = 0;
//
//            if(s.equals("") || s == null){
//                return;
//            }
////            if(clr = true){
////                return;
////            }
////            clr = true;
//
//            String s1 = s.substring(0,s.indexOf(" "));
////        运算符
//            String op = s.substring(s.indexOf(" ")+1,s.indexOf(" ")+2);
////        运算符后的数字
//            String s2 = s.substring(s.indexOf(" ")+3);
//
//            if(!s1.equals("")&&!s2.equals("")) {
//                double d1 = Double.parseDouble(s1);//则数字都是double类型
//                double d2 = Double.parseDouble(s2);
//                if (op.equals("+")) {   //如果是 +
//                    result = d1 + d2;
//                } else if (op.equals("-")) {   //如果是 -
//                    result = d1 - d2;
//                } else if (op.equals("*")) {   //如果是 *
//                    result = d1 * d2;
//                } else if (op.equals("/")) {   //如果是 /
//                    if (d2 == 0) { //如果被除数是0
//                        result = 0; //则结果是0
//                    } else {//否则执行正常是除法运算
//                        result = d1 / d2;
//                    }
//                }
//
//                if (!s1.contains(".") && !s2.contains(".") && !op.equals("/")) {//如果是整数类型
//                    int r = (int) result; //都是整形
//                    text.setText(r+"");
//                } else{
//                    text.setText(result+"");
//                }
//
//            }
//
//            else if(!s1.equals("")&&s2.equals("")){
//                double d1 = Double.parseDouble(s1);
//                if (op.equals("+")){
//                    result = d1;
//                }
//                if (op.equals("-")) {
//                    result = d1;
//                }
//                if (op.equals("*")) {
//                    result = d1;
//                }
//                if (op.equals("/")) {
//                    result = d1;
//                }
//                if(!s1.contains(".")) {
//                    int res = (int) result;
//                    text.setText(res+"");
//                }else {
//                    text.setText(result+"");
//                }
//            }
//
//            else if(s1.equals("")&& !s2.equals("")){
//                double d2 = Double.parseDouble(s2);
//                if(op.equals("+")){
//                    result = 0+d2;
//                }else if(op.equals("-")){
//                    result = 0-d2;
//                }else if(op.equals("*")){
//                    result = 0;
//                }else if(op.equals("/")){
//                    result = 0;
//                }
//                if(!s1.contains(".") && !s2.contains(".")){
//                    int ress = (int) result;
//                    text.setText(ress + "");
//                }else{
//                    text.setText(result + "");
//                }
//            }else{
//                text.setText("");
//            }


    }



}
