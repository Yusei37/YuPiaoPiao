package com.example.yusei.yupiaopiao;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by yusei on 2017/12/15
 */
public class Theatre implements Parcelable {
    private String theatreName;
    private String theatreAddress;
    private String phoneNumber;
    private String theatreDescription;

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getTheatreAddress() {
        return theatreAddress;
    }

    public void setTheatreAddress(String theatreAddress) {
        this.theatreAddress = theatreAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTheatreDescription() {
        return theatreDescription;
    }

    public void setTheatreDescription(String theatreDescription) {
        this.theatreDescription = theatreDescription;
    }

    public Theatre(){
    }

    protected Theatre(Parcel in){
        theatreName = in.readString();
        theatreAddress = in.readString();
        phoneNumber = in.readString();
        theatreDescription = in.readString();
    }

    public static final Creator<Theatre> CREATOR = new Creator<Theatre>() {
        @Override
        public Theatre createFromParcel(Parcel in) {
            return new Theatre(in);
        }

        @Override
        public Theatre[] newArray(int size) {
            return new Theatre[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(theatreName);
        dest.writeString(theatreAddress);
        dest.writeString(phoneNumber);
        dest.writeString(theatreDescription);
    }
}
