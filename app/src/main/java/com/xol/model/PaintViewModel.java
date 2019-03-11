package com.xol.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wwzhang on 2019/3/11
 */
public class PaintViewModel implements Parcelable {

    public PaintViewModel(String mTitle, int mLayoutId) {
        this.mTitle = mTitle;
        this.mLayoutId = mLayoutId;
    }

    public String mTitle;
    public int mLayoutId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeInt(this.mLayoutId);
    }

    protected PaintViewModel(Parcel in) {
        this.mTitle = in.readString();
        this.mLayoutId = in.readInt();
    }

    public static final Parcelable.Creator<PaintViewModel> CREATOR = new Parcelable.Creator<PaintViewModel>() {
        @Override
        public PaintViewModel createFromParcel(Parcel source) {
            return new PaintViewModel(source);
        }

        @Override
        public PaintViewModel[] newArray(int size) {
            return new PaintViewModel[size];
        }
    };
}
