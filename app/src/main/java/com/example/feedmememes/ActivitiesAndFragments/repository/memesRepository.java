package com.example.feedmememes.ActivitiesAndFragments.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse;
import com.example.feedmememes.ActivitiesAndFragments.network.endPoints;
import com.example.feedmememes.ActivitiesAndFragments.network.networkerProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class memesRepository {
    private String TAG="CommonTag";

    private endPoints apiEndPoints= networkerProvider.getInstance().create(endPoints.class);

    public MutableLiveData<trendingMemesResponse> getTrending (){
        final MutableLiveData<trendingMemesResponse> responseFromServer = new MutableLiveData<>();
        Call<trendingMemesResponse> call=  apiEndPoints.getTrendingMemes("i6jz4m1kp7F6vrvSucmIWrQWVWfbJiGC","2","G");
        call.enqueue(new Callback<trendingMemesResponse>() {
            @Override
            public void onResponse(Call<trendingMemesResponse> call, Response<trendingMemesResponse> response) {
                if(response.isSuccessful()){
                    responseFromServer.setValue(response.body());
                    Log.d(TAG," response is successful ");
                }else{
                    Log.d(TAG," response is unsuccessful "+response.code()+" and url called was "+response.raw().request().url());
                }
            }

            @Override
            public void onFailure(Call<trendingMemesResponse> call, Throwable t) {
                Log.d(TAG," failed " +t.getMessage());
            }
        });
        return responseFromServer;
    }

    public MutableLiveData<trendingMemesResponse> getSearchedMemes(String Searchedkey){
        final MutableLiveData<trendingMemesResponse> responseFromServer= new MutableLiveData<>();
        Call<trendingMemesResponse> call= apiEndPoints.getSearchedMemes(Searchedkey,"i6jz4m1kp7F6vrvSucmIWrQWVWfbJiGC","5");
        call.enqueue(new Callback<trendingMemesResponse>() {
            @Override
            public void onResponse(Call<trendingMemesResponse> call, Response<trendingMemesResponse> response) {
                if(response.isSuccessful()){
                    responseFromServer.setValue(response.body());
                    Log.d(TAG," response is successful ");
                }else{
                    Log.d(TAG," response is unsuccessful "+response.code()+" and url called was "+response.raw().request().url());
                }
            }

            @Override
            public void onFailure(Call<trendingMemesResponse> call, Throwable t) {
                Log.d(TAG," failed " +t.getMessage());
            }
        });
        return responseFromServer;
    }
}
