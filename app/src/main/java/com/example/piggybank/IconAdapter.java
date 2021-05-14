package com.example.piggybank;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class IconAdapter extends BaseAdapter {
    private Context mContext;
    //public int[] imageArray={     R.drawable.ic_category_basketball,R.drawable.ic_category_briefcase,R.drawable.ic_category_building,R.drawable.ic_category_gas_pump,R.drawable.ic_category_hotel};
    private ArrayList<String> imageArray;
    private  String folderPath="Category";
    public int selectedPosition;

    public IconAdapter(Context mContext) {
        this.mContext = mContext;

        ArrayList<String> pathList = new ArrayList<>();
        folderPath="Category";
        String[] mImageArray=null;
        try {
            mImageArray = mContext.getAssets().list(folderPath);
            for (String name : mImageArray) {
                pathList.add(folderPath + File.separator + name);
                //Log.e("pathList item", folderPath + File.separator + name);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageArray = new ArrayList<String>(Arrays.asList(mImageArray));

        //System.out.println(imageArray.size());
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



    public void setSelectedPosition(int position)
    {
        this.selectedPosition=position;
        notifyDataSetChanged();

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_category, null);

        RelativeLayout mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.item_category_layout);

        ImageView imageView=(ImageView) convertView.findViewById(R.id.item_category_button);


        //imageView.setImageBitmap(getBitmapFromAsset(imageArray.get(position)) );

        // imageView.setImageResource(imageArray[position]);
        // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //  imageView.setLayoutParams(new GridLayout.LayoutParams());



        imageView.setImageBitmap(getBitmapFromAsset(imageArray.get(position)) );
        if(position==selectedPosition)
        {


            mRelativeLayout.setBackgroundColor(Color.BLUE);        }
        else
        {

            mRelativeLayout.setBackgroundColor(Color.TRANSPARENT);
        }



        return convertView;
    }


}
