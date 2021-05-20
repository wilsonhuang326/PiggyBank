package com.example.piggybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Category> categoryArray;
    private String folderPath = "Category";
    private CategorySQLiteHelper categorySQLiteHelper;

    public CategoryAdapter() {
    }

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
        categorySQLiteHelper = new CategorySQLiteHelper(mContext, null, null, 1);
        categoryArray = categorySQLiteHelper.readAllFromCategoryTable();
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
        ImageView icon = (ImageView) convertView.findViewById(R.id.category_image);
        TextView type = (TextView) convertView.findViewById(R.id.category_type);
        TextView name = (TextView) convertView.findViewById(R.id.category_name);
        ImageButton edit = (ImageButton) convertView.findViewById(R.id.category_edit_button);
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.category_delete_button);

        if (CategoryType.EXPENSE.equalsType(categoryArray.get(position).getType())) {
            type.setText("支出");
        } else if (CategoryType.INCOME.equalsType(categoryArray.get(position).getType())) {
            type.setText("收入");
        }
        name.setText(categoryArray.get(position).getName());
        AssetsHelper assetsHelper=new AssetsHelper(mContext,folderPath);
        String iconPath=categoryArray.get(position).getIconPath();
        icon.setImageBitmap(assetsHelper.getBitmapFromAsset(iconPath));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("edit");
                Intent intent = new Intent(mContext.getApplicationContext(), AddCategory.class);
                intent.putExtra("action", "update");
                intent.putExtra("cid", String.valueOf(categoryArray.get(position).getId()));
                intent.putExtra("cname", categoryArray.get(position).getName());
                intent.putExtra("cpath", categoryArray.get(position).getIconPath());
                intent.putExtra("ctype", categoryArray.get(position).getType());
                mContext.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategorySQLiteHelper categorySQLiteHelper = new CategorySQLiteHelper(mContext, null, null, 1);
                categoryArray = categorySQLiteHelper.deleteFromDB(categoryArray.get(position).getId());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }



    public void readAllFromCategoryTable() {
        categoryArray = categorySQLiteHelper.readAllFromCategoryTable();
        notifyDataSetChanged();

    }

}