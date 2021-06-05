package com.example.common.bookmanager;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    int num;
    String name;
    protected Book(Parcel in) {
        num = in.readInt();
        name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(num);
        dest.writeString(name);
    }

    public void readFromParcel(Parcel replay){
        num = replay.readInt();
        name = replay.readString();
    }
}
