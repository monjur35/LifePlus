package com.example.lifeplus.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.lifeplus.dao.UserssDao;
import com.example.lifeplus.models.UserModel;

public class RoomRepo {

    private UserssDao userDao;

    public RoomRepo(UserssDao userDao) {
        this.userDao = userDao;
    }

    public void registerUser(UserModel userModel){


            userDao.registerUserss(userModel);
           // Log.e("TAG", "registerUser: "+userModel.getName());

    }

    public LiveData<UserModel>logIn(String userName,String password){
        return userDao.login(userName,password);
    }

    public LiveData<UserModel> getUserData(String userName){
        return userDao.getUserData(userName);
    }
}
