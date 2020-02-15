package com.example.feedmememes.ActivitiesAndFragments.dbUtils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.feedmememes.ActivitiesAndFragments.models.memesDBObject;

import java.util.List;

@Dao
public interface memesDBObjectDao {

    @Query("SELECT * FROM meme_table")
    LiveData<List<memesDBObject>> getAll();

    @Query("SELECT * FROM meme_table WHERE type IN (:type)")
    LiveData<List<memesDBObject>> loadAllByIds(String[] type);

    @Insert
    void insertAll(memesDBObject... memes);

    @Delete
    void delete(memesDBObject meme);

    @Insert
    void insert(memesDBObject meme);
}
