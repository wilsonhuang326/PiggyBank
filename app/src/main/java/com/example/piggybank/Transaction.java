package com.example.piggybank;

import java.util.Date;

public class Transaction extends Category{



    private int tid;
    private double amount;
    private Date date;
    private String text;

    public Transaction() {
    }

    public Transaction(int tid,int cid, double amount, Date date, String text, String name, String iconPath, String type) {
        super(cid,name,iconPath,type);
        this.tid=tid;
        this.amount=amount;
        this.date=date;
        this.text=text;
    }
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tid=" + tid +
                ", amount=" + amount +
                ", date=" + date +
                ", text='" + text + '\''+", " +super.toString()+
                '}';
    }
}
