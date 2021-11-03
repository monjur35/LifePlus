package com.example.lifeplus.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lifeplus.models.UserModel;
import com.example.lifeplus.repository.RoomRepo;
import com.example.lifeplus.room.AppDatabase;

public class AuthViewModel extends AndroidViewModel {
    private RoomRepo roomRepo;
    private AppDatabase db;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        db=AppDatabase.getDatabase(application);
        roomRepo=new RoomRepo(db.userDao());
    }

    public void registerUser(UserModel userModel){
        roomRepo.registerUser(userModel);
    }

    public LiveData<UserModel> login(String userName,String pass){
        return roomRepo.logIn(userName,pass);
    }

}
