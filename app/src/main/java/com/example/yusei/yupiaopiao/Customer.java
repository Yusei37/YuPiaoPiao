package com.example.yusei.yupiaopiao;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yusei on 2017/12/8
 */
public class Customer implements Parcelable {
    private String phoneNumber;
    private String password;
    private String customerName;
    private String sex;
    private String customerEmail;
    private String city;
    private java.util.Date registerDate;
    private int VIPLevel;

    public Customer(){
    }

    protected Customer(Parcel in){
        phoneNumber = in.readString();
        password = in.readString();
        customerName = in.readString();
        sex = in.readString();
        customerEmail = in.readString();
        city = in.readString();
        registerDate = new java.util.Date(in.readLong());
        VIPLevel = in.readInt();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public java.util.Date getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(java.util.Date registerDate) {
        this.registerDate = registerDate;
    }
    public int getVIPLevel() {
        return VIPLevel;
    }
    public void setVIPLevel(int VIPLevel) {
        this.VIPLevel = VIPLevel;
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeString(password);
        dest.writeString(customerName);
        dest.writeString(sex);
        dest.writeString(customerEmail);
        dest.writeString(city);
        dest.writeLong(registerDate.getTime());
        dest.writeInt(VIPLevel);
    }
}