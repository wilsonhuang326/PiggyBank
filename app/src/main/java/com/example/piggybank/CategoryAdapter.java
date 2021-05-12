package com.example.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Category> categoryArray;
    private  String folderPath="Category";

    public CategoryAdapter() {
    }

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
        categoryArray=new ArrayList<Category>();
        Category c=new Category("name", "iconPath", "type");
        categoryArray.add(c);
        Category cc=new Category("name1", "iconPath1", "type1");
        categoryArray.add(cc);
    }

    @Override
    public int getCount() {
        return categoryArray.size();
    }


    @Override
    public Object getItem(int position) {
        return categoryArray.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.activity_category_adapter, null);

        LinearLayout mLinearLayout = (LinearLayout) convertView.findViewById(R.id.category_background);

        ImageView icon=(ImageView) convertView.findViewById(R.id.category_image);
        TextView type = (TextView) convertView.findViewById(R.id.category_type);
        TextView  name= (TextView) convertView.findViewById(R.id.category_name);
        ImageButton edit = (ImageButton) convertView.findViewById(R.id.category_edit_button);
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.category_delete_button);
        type.setText("收入");
        name.setText("出行");
        type.setText("income");
        icon.setImageBitmap(getBitmapFromAsset("ic_category_basketball.png") );

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("edit");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("delete");
            }
        });
        return convertView;
    }
    private Bitmap getBitmapFromAsset(String strName)
    {

        AssetManager assetManager = mContext.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(folderPath + File.separator +strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
}