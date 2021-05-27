package com.example.piggybank;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MonthlyTransAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<String> dateArray;
    MySQLiteHelper mySQLiteHelper;
    TransactionAdapter adapter;

    public MonthlyTransAdapter(Context mContext) {
        this.mContext = mContext;
        mySQLiteHelper = new MySQLiteHelper(mContext, null, null, 1);

        dateArray=mySQLiteHelper.getTransDate();

    }
    @Override
    public int getCount() {
        return dateArray.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;   
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_monthlytrans, null);
         ListView  mListView=(ListView)convertView.findViewById(R.id.monthlytransaction_list);
        adapter = new TransactionAdapter(convertView.getContext(),dateArray.get(position));
        mListView.setAdapter(adapter);
        TextView mTextView = (TextView)convertView.findViewById(R.id.items_monthlytrans_date);
        mTextView.setText(dateArray.get(position));
        fixListViewHeight(mListView);
        return convertView;
    }



    public static void fixListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    public void readAllFromCategoryTable() {
        dateArray=mySQLiteHelper.getTransDate();
        notifyDataSetChanged();

    }
}
