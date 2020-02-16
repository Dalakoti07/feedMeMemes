package com.example.feedmememes.ActivitiesAndFragments.dbUtils;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.feedmememes.ActivitiesAndFragments.models.memesDBObject;

import java.util.List;

public class dbRepository {
    private memesDBObjectDao memeDao;
    private LiveData<List<memesDBObject>> mAllWords;

    dbRepository(Application application) {
        appDatabase db = appDatabase.getDatabase(application);
        memeDao = db.getMemesDBObjectDao();
        mAllWords = memeDao.getAll();
    }

    LiveData<List<memesDBObject>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final memesDBObject meme) {
        appDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                memeDao.insert(meme);
            }
        });
    }

    LiveData<List<memesDBObject>> getById(String id){
        return memeDao.getRowById(id);
    }
}
