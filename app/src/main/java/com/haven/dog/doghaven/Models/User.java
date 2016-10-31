package com.haven.dog.doghaven.Models;

/**
 * Created by Stephen Kearns on 31/10/2016.
 * Version 1.0
 */

public class User {
    private String fName, sName, email, addr,date;
    private int mobNum;

    public User()
    {

    }
    public User(String fName, String sName, String email, String addr, String date, int mobNum)
    {
       this.fName = fName;
       this.sName = sName;
       this.email = email;
       this.addr = addr;
       this.date = date;
       this.mobNum = mobNum;

    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMobNum(int mobNum) {
        this.mobNum = mobNum;
    }

    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }

    public String getAddr() {
        return addr;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public int getMobNum() {
        return mobNum;
    }
}
