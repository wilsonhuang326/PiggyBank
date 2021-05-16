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
    public int selectedPosition;

    public CategoryWithNameAdapter() {
    }

    public CategoryWithNameAdapter(Context mContext) {
        this.mContext = mContext;
        categoryArray = new ArrayList<Category>();

        readAllFromCategoryTable();
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

        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_category_image);

        TextView textView = (TextView) convertView.findViewById(R.id.item_category_text);
        //imageView.setImageBitmap(getBitmapFromAsset(imageArray.get(position)) );

        // imageView.setImageResource(imageArray[position]);
        // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //  imageView.setLayoutParams(new GridLayout.LayoutParams());


        imageView.setImageBitmap(getBitmapFromAsset(categoryArray.get(position).getIconPath()));

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

    public Bitmap getBitmapFromAsset(String strName) {

        AssetManager assetManager = mContext.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(folderPath + File.separator + strName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    public void readAllFromCategoryTable() {
        categoryArray.clear();
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(mContext, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        Cursor cursor = dbWriter.query(CategorySQLiteHelper.TABLE_NAME,
                new String[]{CategorySQLiteHelper.FIELD_ID, CategorySQLiteHelper.FIELD_NAME, CategorySQLiteHelper.FIELD_ICON, CategorySQLiteHelper.FIELD_TYPE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ID));
            String name = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_NAME));
            String icon = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ICON));
            String type = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_TYPE));
            Category c = new Category(id, name, icon, type);
            categoryArray.add(c);
            Log.i("CATEGORYWITHNAME", "result: id=" + id + " name: " + name + "  icon:" + icon + " type:" + type);
        }

        cursor.close();
    }

    public void readByTypeFromCategoryTable(int listType) {
        categoryArray.clear();

        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(mContext, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        if (listType == 0) {
            cursor = dbWriter.query(CategorySQLiteHelper.TABLE_NAME,
                    new String[]{CategorySQLiteHelper.FIELD_ID, CategorySQLiteHelper.FIELD_NAME, CategorySQLiteHelper.FIELD_ICON, CategorySQLiteHelper.FIELD_TYPE},
                    CategorySQLiteHelper.FIELD_TYPE + "=?", new String[]{"expense"}, null, null, null);
        }else if(listType==1){
            cursor =dbWriter.query(CategorySQLiteHelper.TABLE_NAME,
                    new String[]{CategorySQLiteHelper.FIELD_ID, CategorySQLiteHelper.FIELD_NAME, CategorySQLiteHelper.FIELD_ICON, CategorySQLiteHelper.FIELD_TYPE},
                    CategorySQLiteHelper.FIELD_TYPE+"=?",new String[]{"income"},null,null,null);
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ID));
            String name = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_NAME));
            String icon = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ICON));
            String type = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_TYPE));
            Category c = new Category(id, name, icon, type);
            categoryArray.add(c);

            Log.i("HUANG", "result: id=" + id + " name: " + name + "  icon:" + icon + " type:" + type);
        }
        Log.d("ARRAYSIZE", String.valueOf(categoryArray.size()));
        cursor.close();
        notifyDataSetChanged();

    }
}
