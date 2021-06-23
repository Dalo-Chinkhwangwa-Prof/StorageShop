package com.dynamicdevz.dynamicstorageapp.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Comic implements Parcelable {

    protected Comic(Parcel in) {
        comicId = in.readInt();
        publishYear = in.readInt();
        rating = in.readDouble();
        title = in.readString();
        issue = in.readInt();
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(comicId);
        parcel.writeInt(publishYear);
        parcel.writeDouble(rating);
        parcel.writeString(title);
        parcel.writeInt(issue);
    }

    public enum Publisher {
        MARVEL,
        DC,
        IMAGE,
        BOOM
    }

    public int getComicId() {
        return comicId;
    }

    private int comicId;
    private int publishYear;
    private Publisher publisher;
    private double rating;
    private String title;
    private int issue;

    public Comic(int comicId, int publishYear, Publisher publisher, double rating, String title, int issue) {
        this.comicId = comicId;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.rating = rating;
        this.title = title;
        this.issue = issue;
    }

    public Comic(int publishYear, Publisher publisher, double rating, String title, int issue) {
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.rating = rating;
        this.title = title;
        this.issue = issue;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public int getIssue() {
        return issue;
    }
}
