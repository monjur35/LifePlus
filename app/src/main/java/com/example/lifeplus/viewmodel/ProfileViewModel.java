package com.example.lifeplus.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lifeplus.models.UserModel;
import com.example.lifeplus.repository.RoomRepo;
import com.example.lifeplus.room.AppDatabase;

public class ProfileViewModel extends AndroidViewModel  {

    private RoomRepo roomRepo;
    private AppDatabase db;

    public ProfileViewModel(@NonNull Application application) {
        super(application);

        db=AppDatabase.getDatabase(application);
        roomRepo=new RoomRepo(db.userDao());
    }

    public LiveData<UserModel> getUserData(String userName){
        return roomRepo.getUserData(userName);
    }


}
