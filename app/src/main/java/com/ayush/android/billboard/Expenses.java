package com.ayush.android.billboard;

public class Expenses {

    private int tid;
    private String category;
    private int price;

    public Expenses() {
    }

    public Expenses(int tid, String category, int price) {
        this.tid = tid;
        this.category = category;
        this.price = price;
    }

    public void setTid (int tid) {
        this.tid = tid;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public void setPrice (int price) {
        this.price = price;
    }

    public int getTid () {
        return tid;
    }

    public String getCategory () {
        return category;
    }

    public int getPrice () {
        return price;
    }

}

