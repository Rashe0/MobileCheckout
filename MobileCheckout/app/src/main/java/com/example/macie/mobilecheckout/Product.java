package com.example.macie.mobilecheckout;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by macie on 13-May-17.
 */

public class Product implements Parcelable {
    private String barcode;
    private String name;
    private Long price;
    private String color;
    private String imageUrl;

    public static final Parcelable.Creator<Product> CREATOR =
            new Parcelable.Creator<Product>() {
                @Override
                public Product createFromParcel(Parcel in) {
                    return new Product(in);
                }

                @Override
                public Product[] newArray(int size) {
                    return new Product[size];
                }
            };

    public Product(String barcode, String name, Long price, String color, String imageUrl) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.color = color;
        this.imageUrl = imageUrl;
    }

    private Product(Parcel in) {
        barcode = in.readString();
        name = in.readString();
        price = in.readLong();
        color = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(barcode);
        out.writeString(name);
        out.writeLong(price);
        out.writeString(color);
        out.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public String getBarcode() { return barcode; }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
