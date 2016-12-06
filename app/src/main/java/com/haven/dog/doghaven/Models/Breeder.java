package com.haven.dog.doghaven.Models;

/**
 * Created by kearn on 10/11/2016.
 */

public class Breeder {
    private String companyname, companyvatnum, email, addr, password;
    private int mobNum;

    public Breeder()
    {

    }
    public Breeder(String companyname, String password)
    {
        this.companyname = companyname;
        this.password = password;
    }

    public Breeder( String companyname,
                   String companyvatnum, String email, String paasword, String addr)
    {
        this.companyname = companyname;
        this.companyvatnum = companyvatnum;
        this.email = email;
        this.addr = addr;
        this.password = paasword;

    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }


    public void setMobNum(int mobNum) {
        this.mobNum = mobNum;
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



    public String getAddr() {
        return addr;
    }


    public String getEmail() {
        return email;
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
