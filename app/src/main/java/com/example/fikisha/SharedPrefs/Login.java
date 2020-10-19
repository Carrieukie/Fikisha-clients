package com.example.fikisha.SharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fikisha.Models.Order;
import com.example.fikisha.Models.User;

import java.util.ArrayList;

public class Login {


    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("login", Context.MODE_PRIVATE);
    }


    public  void saveObject(Context context, User user) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("id", user.getId());
        editor.putString("name", user.getName());
        editor.commit();
    }

    public void setString(Context context, String string){
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("img", string);
        editor.commit();
    }

    public void clear(Context context){
        getSharedPreferences(context).edit().clear().commit();
    }

    public String get(Context context, String string){
        return getSharedPreferences(context).getString(string,"Not Found");
    }

}
