package com.example.lifeplus.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.lifeplus.reponse.shows.search.SearchResponse;
import com.example.lifeplus.reponse.shows.single.ShowsResponse;
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


    public MutableLiveData <List<SearchResponse>>getAllShow(String s){

        MutableLiveData<List<SearchResponse>> listMutableLiveData=new MutableLiveData<>();

        apiInterface.getAllShows(s).enqueue(new Callback<List<SearchResponse>>() {
            @Override
            public void onResponse(Call<List<SearchResponse>> call, Response<List<SearchResponse>> response) {
                if (response.isSuccessful()){
                    listMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SearchResponse>> call, Throwable t) {

                Log.e(TAG, "onFailure: all shows"+t.getLocalizedMessage() );
            }
        });

        return listMutableLiveData;

    }
    public MutableLiveData<ShowsResponse>getshowById(String id){
        MutableLiveData<ShowsResponse>showsResponseMutableLiveData=new MutableLiveData<>();

        apiInterface.getShowbyId(id).enqueue(new Callback<ShowsResponse>() {
            @Override
            public void onResponse(Call<ShowsResponse> call, Response<ShowsResponse> response) {
                if (response.isSuccessful()){
                    showsResponseMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ShowsResponse> call, Throwable t) {

                Log.e(TAG, "onFailure: single show"+t.getLocalizedMessage() );
            }
        });

        return showsResponseMutableLiveData;
    }

}
