package com.example.feedmememes.ActivitiesAndFragments.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse;
import com.example.feedmememes.ActivitiesAndFragments.network.endPoints;
import com.example.feedmememes.ActivitiesAndFragments.network.networkerProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class memesViewModel extends AndroidViewModel {
    private String TAG="CommonTag";
    public memesViewModel(@NonNull Application application) {
        super(application);
    }

    private endPoints apiEndPoints= networkerProvider.getInstance().create(endPoints.class);

    public MutableLiveData<trendingMemesResponse> getTrending (){
        final MutableLiveData<trendingMemesResponse> responseFromServer = new MutableLiveData<>();
        Call<trendingMemesResponse> call=  apiEndPoints.getTrendingMemes("i6jz4m1kp7F6vrvSucmIWrQWVWfbJiGC","5","G");
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
