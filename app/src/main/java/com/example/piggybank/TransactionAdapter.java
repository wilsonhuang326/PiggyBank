package com.example.piggybank;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Transaction> transactionArray;
    private String folderPath = "Category";
    private MySQLiteHelper mySQLiteHelper;

    //    public TransactionAdapter(Context mContext) {
//        this.mContext = mContext;
//        mySQLiteHelper = new MySQLiteHelper(mContext, null, null, 1);
//        transactionArray = mySQLiteHelper.readAllFromTransactionTable();
//    }
    public TransactionAdapter(Context mContext, String date) {
        this.mContext = mContext;
        mySQLiteHelper = new MySQLiteHelper(mContext, null, null, 1);
        transactionArray = mySQLiteHelper.readByDateFromTransactionTable(date);

    }

    @Override
    public int getCount() {
        return transactionArray.size();
    }

    @Override
    public Object getItem(int position) {
        return transactionArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_transaction, null);
        ImageView icon = (ImageView) convertView.findViewById(R.id.item_transaction_image);
        TextView typename = (TextView) convertView.findViewById(R.id.item_transaction_name);
        TextView notes = (TextView) convertView.findViewById(R.id.item_transaction_notes);
        TextView amount = (TextView) convertView.findViewById(R.id.item_transaction_amount);
//        if (CategoryType.EXPENSE.equalsType(transactionArray.get(position).getType())) {
//            typename.setText("支出");
//        } else if (CategoryType.INCOME.equalsType(transactionArray.get(position).getType())) {
//            typename.setText("收入");
//        }
        typename.setText(transactionArray.get(position).getName());

        notes.setText(transactionArray.get(position).getText());
        AssetsHelper assetsHelper = new AssetsHelper(mContext, folderPath);
        String iconPath = transactionArray.get(position).getIconPath();
        icon.setImageBitmap(assetsHelper.getBitmapFromAsset(iconPath));
        amount.setText(String.valueOf(transactionArray.get(position).getAmount()));
//
//        Log.d("test date", String.valueOf(position));


        return convertView;
    }

    public void readAllFromCategoryTable() {
        transactionArray = mySQLiteHelper.readAllFromTransactionTable();
        notifyDataSetChanged();

    }

    public double getExpenseTotal() {

        double total = 0;
        for (Transaction transaction : transactionArray) {
            if (CategoryType.EXPENSE.equalsType(transaction.getType())) {
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public double getIncomeTotal() {
        double total = 0;
        for (Transaction transaction : transactionArray) {
            if (CategoryType.INCOME.equalsType(transaction.getType())) {
                total += transaction.getAmount();
            }
        }
        return total;
    }

}
