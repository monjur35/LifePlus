package com.example.lifeplus.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.lifeplus.reponse.shows.search.SearchResponse;
import com.example.lifeplus.reponse.shows.single.ShowsResponse;
import com.example.lifeplus.repository.ApiRepository;
import com.example.lifeplus.restAPI.APIClient;
import com.example.lifeplus.restAPI.ApiInterface;

import java.util.List;

public class DashViewModel extends AndroidViewModel {

    private ApiRepository apiRepository;
    private ApiInterface apiInterface;

    public DashViewModel(@NonNull Application application) {
        super(application);
        apiInterface= APIClient.getClient().create(ApiInterface.class);
        apiRepository=new ApiRepository(apiInterface);
    }

    public MutableLiveData<List<SearchResponse>> getAllShow(String s){
        return apiRepository.getAllShow(s);
    }

    public MutableLiveData<ShowsResponse>getShowById(String id){
        return apiRepository.getshowById(id);
    }
}
