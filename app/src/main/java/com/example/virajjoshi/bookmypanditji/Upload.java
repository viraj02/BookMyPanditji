package com.example.virajjoshi.bookmypanditji;

/**
 * Created by VIRAJ JOSHI on 19-02-2018.
 */

public class Upload {

    public String name;
    public String url;
    public String date;
    public String address;
    public String email;
    public String mobile;
    public String exp;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url,String date, String address , String email, String mobile , String exp) {
        this.name = name;
        this.url= url;
        this.date = date;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getExp() {
        return exp;
    }
}
