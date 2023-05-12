package com.example.demo;

public class AccountBean {
    private String name;
    private int visaCardNum;
    private String address;

    public String getName(){
        return name;
    }

    public int getVisaCardNum(){
        return visaCardNum;
    }

    public String getAddress(){
        return address;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setVisaCardNum(int visaCardNum) {
        this.visaCardNum = visaCardNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
