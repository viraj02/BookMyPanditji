package com.example.virajjoshi.bookmypanditji;

/**
 * Created by VIRAJ JOSHI on 16-02-2018.
 */

public class Artist {

    String OrderID;
    String Name;

    String Address;    //
    String Date;   //
    String Time;  //
    String Email;

    String Mobile;
    String TypeOfPooja;
    String TotalAmount;


    public Artist(){

    }

    public Artist(String orderId, String userName, String UAddress, String dateofPooja, String timeofPooja, String UEmail,String UMobile, String TypeOfP,String totalprice){

        this.OrderID= orderId;
        this.Name = userName;
        this.Address = UAddress;
        this.Date = dateofPooja;
        this.Time = timeofPooja;
        this.Email = UEmail;

        this.Mobile = UMobile;
        this.TypeOfPooja = TypeOfP;
        this.TotalAmount = totalprice;


    }

    public String getOrderID() {
        return OrderID;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getTypeOfPooja() {
        return TypeOfPooja;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }
}

