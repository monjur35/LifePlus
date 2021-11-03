package com.example.lifeplus.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.lifeplus.reponse.shows.ShowsResponse;
import com.example.lifeplus.restAPI.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {
     private ApiInterface apiInterface;
    public static String TAG="TAG";

    public ApiRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }
    public MutableLiveData <List<ShowsResponse>>getAllShow(int page){

        MutableLiveData<List<ShowsResponse>> listMutableLiveData=new MutableLiveData<>();

        apiInterface.getAllShows(page).enqueue(new Callback<List<ShowsResponse>>() {
            @Override
            public void onResponse(Call<List<ShowsResponse>> call, Response<List<ShowsResponse>> response) {
                if (response.isSuccessful()){
                    listMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ShowsResponse>> call, Throwable t) {

                Log.e(TAG, "onFailure: all shows"+t.getLocalizedMessage() );
            }
        });

        return listMutableLiveData;

    }

}
