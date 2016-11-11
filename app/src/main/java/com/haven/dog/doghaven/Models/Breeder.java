package com.haven.dog.doghaven.Models;

/**
 * Created by kearn on 10/11/2016.
 */

public class Breeder {
    private String fName, sName, username,companyname, companyvatnum, email, addr, dob, password, usertype;
    private int mobNum;

    public Breeder()
    {

    }
    public Breeder(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Breeder(String fName, String sName, String username, String companyname,
                   String companyvatnum, String email, String paasword, String addr,
                   String dob)
    {
        this.fName = fName;
        this.sName = sName;
        this.username = username;
        this.companyname = companyname;
        this.companyvatnum = companyvatnum;
        this.email = email;
        this.addr = addr;
        this.dob = dob;
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

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setCompanyvatnum(String companyvatnum) {
        this.companyvatnum = companyvatnum;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getCompanyvatnum() {
        return companyvatnum;
    }
}
