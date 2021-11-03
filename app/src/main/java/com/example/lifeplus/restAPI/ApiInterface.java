package com.example.lifeplus.restAPI;

import com.example.lifeplus.reponse.shows.search.SearchResponse;
import com.example.lifeplus.reponse.shows.single.ShowsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search/shows")
    Call<List<SearchResponse>> getAllShows(
            @Query("q") String str
    );

    @GET("shows/{id}")
    Call <ShowsResponse> getShowbyId(
            @Path("id") String id
    );



}
