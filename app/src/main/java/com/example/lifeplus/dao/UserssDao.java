package com.example.lifeplus.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lifeplus.models.UserModel;

import java.util.List;

@Dao
public interface UserssDao {

    @Insert
    void registerUserss(UserModel userModel);


    @Query("select * from user_table where userName=:userName and password= :pass")
    LiveData<UserModel> login(String userName,String pass);






    @Update
    int updateUserss(UserModel userModel);

    @Delete
    int deleteUserss(UserModel userModel);

    @Query("DELETE FROM user_table")
    void deletessAllData();

    @Query("select * from user_table")
    LiveData<List<UserModel>> getAllUserss();




}
