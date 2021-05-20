package com.example.piggybank;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CategoryWithNameAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Category> categoryArray;
    private String folderPath = "Category";
    private int selectedPosition;
    private CategorySQLiteHelper categorySQLiteHelper;

    public CategoryWithNameAdapter() {
    }

    public CategoryWithNameAdapter(Context mContext) {
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
        convertView = View.inflate(mContext, R.layout.item_category, null);
        LinearLayout mRelativeLayout = (LinearLayout) convertView.findViewById(R.id.item_category_layout);
        ImageView icon = (ImageView) convertView.findViewById(R.id.item_category_image);
        TextView textView = (TextView) convertView.findViewById(R.id.item_category_text);

        AssetsHelper assetsHelper=new AssetsHelper(mContext,folderPath);
        String iconPath=categoryArray.get(position).getIconPath();
        icon.setImageBitmap(assetsHelper.getBitmapFromAsset(iconPath));

        textView.setText(categoryArray.get(position).getName());
        if (position == selectedPosition) {
            mRelativeLayout.setBackgroundColor(Color.BLUE);
        } else {
            mRelativeLayout.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

    public void readAllFromCategoryTable() {
        categoryArray = categorySQLiteHelper.readAllFromCategoryTable();
        notifyDataSetChanged();
    }

    public void readByTypeFromCategoryTable(CategoryType listType) {
        categoryArray = categorySQLiteHelper.readByTypeFromCategoryTable(listType);
        notifyDataSetChanged();

    }
}
