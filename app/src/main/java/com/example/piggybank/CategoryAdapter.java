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
    private  String folderPath="Category";

    public CategoryAdapter() {
    }

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
        categoryArray=new ArrayList<Category>();

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
        convertView = View.inflate(mContext, R.layout.activity_category_adapter, null);

        LinearLayout mLinearLayout = (LinearLayout) convertView.findViewById(R.id.category_background);
        ImageView icon=(ImageView) convertView.findViewById(R.id.category_image);
        TextView type = (TextView) convertView.findViewById(R.id.category_type);
        TextView  name= (TextView) convertView.findViewById(R.id.category_name);
        ImageButton edit = (ImageButton) convertView.findViewById(R.id.category_edit_button);
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.category_delete_button);
      /*
        type.setText("收入");
        name.setText("出行");

       */
        type.setText(categoryArray.get(position).getType());
        name.setText(categoryArray.get(position).getName());
        icon.setImageBitmap(getBitmapFromAsset(categoryArray.get(position).getIconPath()) );

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("edit");
                Intent intent = new Intent(mContext.getApplicationContext(), AddCategory.class);
                intent.putExtra("action","update");
                intent.putExtra("cid",String.valueOf(categoryArray.get(position).getId()));
                intent.putExtra("cname",categoryArray.get(position).getName());
                intent.putExtra("cpath",categoryArray.get(position).getIconPath());

                intent.putExtra("ctype",categoryArray.get(position).getType());

                mContext.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("delete");
              deleteFromDB(position);

            }
        });
        return convertView;
    }

    private void deleteFromDB(int position){
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(mContext, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        System.out.println("id"+categoryArray.get(position).getId());
        dbWriter.delete(CategorySQLiteHelper.TABLE_NAME,CategorySQLiteHelper.FIELD_ID+"=?",new String[]{String.valueOf(categoryArray.get(position).getId())});
        readAllFromCategoryTable();
        CategoryAdapter.this.notifyDataSetChanged();
    }
    public Bitmap getBitmapFromAsset(String strName)
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
    public void readAllFromCategoryTable(){
        categoryArray.clear();
        SQLiteOpenHelper dbHelper = new CategorySQLiteHelper(mContext, null, null, 1);
        SQLiteDatabase dbWriter = dbHelper.getWritableDatabase();
        Cursor cursor =dbWriter.query(CategorySQLiteHelper.TABLE_NAME,
                new String[]{CategorySQLiteHelper.FIELD_ID, CategorySQLiteHelper.FIELD_NAME, CategorySQLiteHelper.FIELD_ICON, CategorySQLiteHelper.FIELD_TYPE},
                null,null,null,null,null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ID));
            String name = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_NAME));
            String icon = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_ICON));
            String type = cursor.getString(cursor.getColumnIndex(CategorySQLiteHelper.FIELD_TYPE));
            Category c=new Category(id,name, icon, type);
            categoryArray.add(c);
            Log.i("Mainactivity","result: id="  + id +" name: " + name +"  icon:" + icon+" type:"+type);
        }

        cursor.close();
    }
}