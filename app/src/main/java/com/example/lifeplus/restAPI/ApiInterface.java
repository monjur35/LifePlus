package com.example.lifeplus.restAPI;

import com.example.lifeplus.reponse.shows.ShowsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("shows")
    Call<List<ShowsResponse>> getAllShows(
            @Query("page") int page
    );

    @GET("shows/{id}")
    Call <ShowsResponse> getShowbyId(
            @Path("id") String id
    );



}
