package com.example.piggybank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Category> categoryArray;
    private String folderPath = "Category";
    private MySQLiteHelper mySQLiteHelper;

    public CategoryAdapter() {
    }

    public CategoryAdapter(Context mContext) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_category_management, null);

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
                intent.putExtra("cid", String.valueOf(categoryArray.get(position).getCid()));
                intent.putExtra("cname", categoryArray.get(position).getName());
                intent.putExtra("cpath", categoryArray.get(position).getIconPath());
                intent.putExtra("ctype", categoryArray.get(position).getType());
                mContext.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UpdateDeleteDialog cdd = new UpdateDeleteDialog(mContext,"确定要删除本分类吗？", categoryArray.get(position).getCid());
//
//                cdd.show();
               showDialog(position);
            }
        });
        return convertView;
    }
    private void showDialog(int position) {
        new AlertDialog.Builder(mContext)
                .setMessage("确定要删除这个分类吗")
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(mContext, null, null, 1);
                        categoryArray = mySQLiteHelper.deleteFromCategoryTable(categoryArray.get(position).getCid());
                        notifyDataSetChanged();
                        Toast.makeText(mContext, "成功删除。",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("否", null)
                .show();

    }


    public void readAllFromCategoryTable() {
        categoryArray = mySQLiteHelper.readAllFromCategoryTable();
        notifyDataSetChanged();

    }

}