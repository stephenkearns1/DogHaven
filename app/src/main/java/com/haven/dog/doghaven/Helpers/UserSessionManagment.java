package com.haven.dog.doghaven.Helpers;

/**
 * Created by kearn on 06/12/2016.
 */


import android.content.Context;
import android.content.SharedPreferences;

import com.haven.dog.doghaven.Models.Breeder;
import com.haven.dog.doghaven.Models.User;

public class UserSessionManagment {

        public static final String SP_NAME = "UserSession";
        SharedPreferences userSessionManager; //used so data can be stored locally
        static User user;
        static Breeder breeder;

        public UserSessionManagment(Context context){
            userSessionManager = context.getSharedPreferences(SP_NAME,0);
        }

        //stores users details locally
        public void SessionUserMangement(User user){
            this.user = user;
            SharedPreferences.Editor spEditor = userSessionManager.edit();

            spEditor.putString("fname", user.getfName());
            spEditor.putString("sname", user.getfName());
            spEditor.putString("userName", user.getUsername());
            spEditor.putString("email", user.getEmail());
            spEditor.putString("password", user.getPassword());
            spEditor.commit();
        }

    public void BreederSessionManagment(Breeder breeder) {
          this.breeder = breeder;
          SharedPreferences.Editor spEditor = userSessionManager.edit();

          spEditor.putString("companyname", breeder.getCompanyname());
          spEditor.putString("companyemail", breeder.getEmail());
          spEditor.putString("addr", breeder.getEmail());
          spEditor.putString("companypassword",breeder.getPassword());

          spEditor.commit();
    }
        //checks if users is logged in i,e true of if not i,e false
        public User UserLoggedIn(){
            String fname = userSessionManager.getString("fname", "");
            String sname = userSessionManager.getString("sname", "");
            String userName = userSessionManager.getString("userName", "");
            String email = userSessionManager.getString("email", "");
            String password = userSessionManager.getString("password", "");

            user = new User(fname,sname,userName,email,password);
            return user;
        }

        //set user who is logged in
        public boolean getLoggedIn(){
            if(userSessionManager.getBoolean("LoggedIn", false)== true){
                return true;
            }else{
                return false;
            }
        }

        public void setUserLoggedIn(boolean isLogged){
            SharedPreferences.Editor spEditor = userSessionManager.edit();
            spEditor.putBoolean("LoggedIn",isLogged);
            spEditor.commit();
        }


        //clear cached user data when loggin out
        public void clearUserData(){
            SharedPreferences.Editor spEditor = userSessionManager.edit();
            spEditor.clear();
            spEditor.commit();
        }




    //checks if users is logged in i,e true of if not i,e false
    public Breeder BreederLoggedIn(){
        String companyname = userSessionManager.getString("companyname", "");
        String companyemail = userSessionManager.getString("companyemail", "");
        String companyaddr = userSessionManager.getString("addr", "");
        String companypassword = userSessionManager.getString("companypassword", "");

        breeder = new Breeder(companyname, companyemail, companyaddr, companypassword);

        return breeder;
    }

    //set user who is logged in
    public boolean getBreederLoggedIn(){
        if(userSessionManager.getBoolean("UserLoggedIn", false)== true){
            return true;
        }else{
            return false;
        }
    }

    public void setBreederLoggedIn(boolean isLogged){
        SharedPreferences.Editor spEditor = userSessionManager.edit();
        spEditor.putBoolean("BreederLoggedIn",isLogged);
        spEditor.commit();
    }


    //clear cached user data when loggin out
    public void clearBreederData(){
        SharedPreferences.Editor spEditor = userSessionManager.edit();
        spEditor.clear();
        spEditor.commit();
    }


    }














































