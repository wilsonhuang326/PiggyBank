package com.example.piggybank;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryWithNameAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Category> categoryArray;
    private String folderPath = "Category";
    private int selectedPosition;
    private MySQLiteHelper mySQLiteHelper;

    public CategoryWithNameAdapter() {
    }

    public CategoryWithNameAdapter(Context mContext) {
        this.mContext = mContext;
        mySQLiteHelper = new MySQLiteHelper(mContext, null, null, 1);
        categoryArray = mySQLiteHelper.readAllFromCategoryTable();
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
        return categoryArray.get(position).getCid();
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
        categoryArray = mySQLiteHelper.readAllFromCategoryTable();
        notifyDataSetChanged();
    }

    public void readByTypeFromCategoryTable(CategoryType listType) {
        categoryArray = mySQLiteHelper.readByTypeFromCategoryTable(listType);
        notifyDataSetChanged();

    }
}
