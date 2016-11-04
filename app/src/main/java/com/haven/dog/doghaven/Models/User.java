package com.haven.dog.doghaven.Models;

/**
 * Created by Stephen Kearns on 31/10/2016.
 * Version 1.0
 */

public class User {
    private String fName, sName, username, email, addr, dob, password, usertype;
    private int mobNum;

    public User()
    {

    }
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public User(String fName, String sName, String username, String email, String paasword, String addr, String dob, int mobNum)
    {
       this.fName = fName;
       this.sName = sName;
       this.username = username;
       this.email = email;
       this.addr = addr;
       this.dob = dob;
       this.mobNum = mobNum;
       this.password = paasword;

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

    public void setDob(String date) {
        dob = date;
    }

    public void setMobNum(int mobNum) {
        this.mobNum = mobNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
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

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public int getMobNum() {
        return mobNum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsertype() {
        return usertype;
    }
}
