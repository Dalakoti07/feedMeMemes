package com.example.feedmememes.ActivitiesAndFragments.network;

import com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface endPoints {
    @GET("/v1/gifs/trending")
    Call<trendingMemesResponse> getTrendingMemes(@Query("api_key") String api_key, @Query("limit") String limit, @Query("rating") String rating);
}
