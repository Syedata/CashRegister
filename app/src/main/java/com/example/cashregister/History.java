package com.example.cashregister;

import android.os.Parcel;

public class History extends Product {
    private String date;
    private int quantityPurchased;
    private double totalPrice;

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public History(String name, String date, int quantityPurchased, double totalPrice) {
        super();
        this.name = name;
        this.date = date;
        this.quantityPurchased = quantityPurchased;
        this.totalPrice = totalPrice;
    }

    protected History(Parcel in) {
        super(in);
        date = in.readString();
        quantityPurchased = in.readInt();
        totalPrice = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(date);
        dest.writeInt(quantityPurchased);
        dest.writeDouble(totalPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };
}
