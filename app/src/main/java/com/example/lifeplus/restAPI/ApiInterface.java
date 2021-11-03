package com.moinul.digitaluno.restApi;

import com.moinul.digitaluno.response.login.assign.AssignResponse;
import com.moinul.digitaluno.response.login.blog.BlogResponse;
import com.moinul.digitaluno.response.login.complainList.ComplainListResponse;
import com.moinul.digitaluno.response.login.login.LoginResponse;
import com.moinul.digitaluno.response.login.complainRes.ComplainResponse;
import com.moinul.digitaluno.response.login.problemlist.ProblemListResponse;
import com.moinul.digitaluno.response.login.summery.SummeryResponse;
import com.moinul.digitaluno.response.login.unomeeting.UNOmeetingResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("user/login")
    Call<LoginResponse>login(

            @Query("email") String email,
            @Query("password") String pass
    );


    @GET("complain/problemlist")
    Call<ProblemListResponse>problemListResponse();

    @Multipart
    @POST("add/complain")
    Call<ComplainResponse> complainRes(

            @Query("user_name") String userName,
            @Query("user_phone") String phone,
            @Query("user_nid") String nid,
            @Query("complain_type") String type,
            @Query("complain_details") String details,
            @Part MultipartBody.Part part

    );

    @POST("complain/list")
    Call<ComplainListResponse> complainList(
            @Query("page") int page,
            @Query("user_id") String userId

    );

    @GET("report/summery")
    Call<SummeryResponse> summeryResponse(

    );

    @GET("blog/list")
    Call<BlogResponse>getBlogs(
            @Query("page") int page
    );

    @POST("complain/assign")
    Call<AssignResponse> getAssignResponse(
            @Query("mobile") String mobile,
            @Query("complain_id") String complainId
    );

    @GET("complain/public/list")
    Call<UNOmeetingResponse> getUnoMeet(
            @Query("page") int page
    );

}
