package com.haven.dog.doghaven.Helpers;

import android.util.Log;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kearn on 06/12/2016.
 */

public class Validation {

    public Validation(){

    }

    public boolean IsVaildEmail(String email){

        String emailFormat = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]$";
        Pattern pattern = Pattern.compile(emailFormat, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.matches();
        Log.i("email matching", ":" + matches);
        Pattern pattern2 = Patterns.EMAIL_ADDRESS;
        return pattern2.matcher(email).matches();

        //return !TextUtils.isEmpty(password) && android.util.Patterns.EMAIL_ADDRESS.matcher(password).matches();
        /* OR


               return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
}


         */

        // return matcher.matches();
    }

    public boolean IsVaildPassword (String password){

        String passwordFormat = "((?=.*\\d)(?=.*[a-z]).{6,30})";
        Pattern pattern = Pattern.compile(passwordFormat);
        Matcher matcher = pattern.matcher(password);
        boolean matches = matcher.matches();
        Log.i("Password matching", ":" + matches);
        return matcher.matches();
    }

}
