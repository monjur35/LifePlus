package com.example.lifeplus.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lifeplus.dao.UserssDao;

public class StoreDataPreference {

    public static  StoreDataPreference storeDataPreference;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;

    private static final String PREF_NAME = "LifePlus";
    private static  final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_SEARCH = "searchKeyWord";


    public StoreDataPreference(Context context) {
        pref=context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static StoreDataPreference getInstance(Context context){
        if (storeDataPreference==null){
            storeDataPreference=new StoreDataPreference(context);

        }
        return storeDataPreference;
    }

    public void setLoginStatus(Boolean loginStatus){
        editor=pref.edit();
        editor.putBoolean(IS_LOGIN,loginStatus);
        editor.apply();

    }
    public void setSearchKey(String searchKey){
        editor=pref.edit();
        editor.putString(KEY_SEARCH,searchKey);
        editor.apply();
    }

    public void setUserName(String userName){
        editor=pref.edit();
        editor.putString(KEY_USER_NAME,userName);
        editor.apply();
    }


    public  Boolean getLogInStatus(){
        return pref.getBoolean(IS_LOGIN,false);
    }

    public  String getKeyUserName() {
        return storeDataPreference.pref.getString(KEY_USER_NAME,"");
    }
    public  String getKeySearch() {
        return storeDataPreference.pref.getString(KEY_SEARCH,"");
    }



    public void logoutUser(){
        editor=pref.edit();
        editor.clear();
        editor.putBoolean(IS_LOGIN,false);
        editor.apply();



    }


}
