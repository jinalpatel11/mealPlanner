package com.example.mealplanner.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GoalItem implements Parcelable {
    private int imageResource;
    private String text;

    public GoalItem(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }

    // Parcelable implementation
    protected GoalItem(Parcel in) {
        imageResource = in.readInt();
        text = in.readString();
    }

    public static final Creator<GoalItem> CREATOR = new Creator<GoalItem>() {
        @Override
        public GoalItem createFromParcel(Parcel in) {
            return new GoalItem(in);
        }

        @Override
        public GoalItem[] newArray(int size) {
            return new GoalItem[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResource);
        dest.writeString(text);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
