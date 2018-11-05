package com.example.domis.android_app;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class PersonalData extends AppCompatActivity {
    public static User USER;
    public static LoginActivity LOGIN_ACTIVITY;

    public static void validate(String password){
        if(USER != null)
            if (USER.getPassword().equals(password))
                LOGIN_ACTIVITY.successLogin();
            else
                Log.e("Error", "Wrong Password");
        else
            Log.e("Error:", "User NOT Found");
    }


}
