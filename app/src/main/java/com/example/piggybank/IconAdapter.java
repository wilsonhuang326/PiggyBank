package com.example.piggybank;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class IconAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> imageArray;
    private String folderPath = "Category";
    public int selectedPosition;

    public IconAdapter(Context mContext) {
        this.mContext = mContext;

        ArrayList<String> pathList = new ArrayList<>();
        String[] mImageList = null;
        try {
            mImageList = mContext.getAssets().list(folderPath);
            for (String name : mImageList) {
                pathList.add(folderPath + File.separator + name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageArray = new ArrayList<String>(Arrays.asList(mImageList));
    }

    @Override
    public int getCount() {
        return imageArray.size();
    }

    @Override
    public Object getItem(int position) {
        return imageArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();

    }
    /*
    private int getIndexOfIcon(String path) {
        for (int i = 0; i < imageArray.size(); i++)
            if (imageArray.get(i).getCustomer().getAccountNum() == searchActNum)
                return i;
        return -1;
    }

     */
    public int setSelectedPosition(String path) {
        int index = imageArray.indexOf(path);
        this.selectedPosition = index;
        System.out.println(index);
        notifyDataSetChanged();
        return index;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_icon, null);
        RelativeLayout mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.item_icon_layout);
        ImageView icon = (ImageView) convertView.findViewById(R.id.item_icon_button);

        AssetsHelper assetsHelper=new AssetsHelper(mContext,folderPath);
        String iconPath=imageArray.get(position);
        icon.setImageBitmap(assetsHelper.getBitmapFromAsset(iconPath));

        if (position == selectedPosition) {
            mRelativeLayout.setBackgroundColor(Color.BLUE);
        } else {
            mRelativeLayout.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }


}
