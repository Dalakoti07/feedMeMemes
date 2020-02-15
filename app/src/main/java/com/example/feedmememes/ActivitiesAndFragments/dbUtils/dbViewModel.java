package com.example.feedmememes.ActivitiesAndFragments.dbUtils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.feedmememes.ActivitiesAndFragments.models.memesDBObject;

import java.util.List;

public class dbViewModel extends AndroidViewModel {
    private dbRepository mRepository;

    private LiveData<List<memesDBObject>> mAllWords;
    public dbViewModel(@NonNull Application application) {
        super(application);
        mRepository=new dbRepository(application);
        mAllWords=mRepository.getAllWords();
    }

    public LiveData<List<memesDBObject>> getAllWords() { return mAllWords; }

    public void insert(memesDBObject word) { mRepository.insert(word); }
}
