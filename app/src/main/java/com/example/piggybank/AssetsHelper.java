package com.example.piggybank;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AssetsHelper {
    private Context mContext;
    private String folderPath;

    public AssetsHelper() {
    }

    public AssetsHelper(Context mContext, String folderPath) {
        this.mContext = mContext;
        this.folderPath = folderPath;
    }

    public  Bitmap getBitmapFromAsset( String strName) {
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
}
