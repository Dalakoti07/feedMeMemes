package com.example.feedmememes.ActivitiesAndFragments.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse;
import com.example.feedmememes.ActivitiesAndFragments.repository.memesRepository;

public class memesViewModel extends AndroidViewModel {
    private memesRepository memesRepository = new memesRepository();
    private String TAG="CommonTag";
    public memesViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<trendingMemesResponse> getTrending (){
        return memesRepository.getTrending();
    }

    public MutableLiveData<trendingMemesResponse> getSearchedMemes(String searchedTerm){
        return memesRepository.getSearchedMemes(searchedTerm);
    }
}
