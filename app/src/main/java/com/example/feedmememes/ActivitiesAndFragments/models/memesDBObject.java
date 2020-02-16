package com.example.feedmememes.ActivitiesAndFragments.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meme_table")
public class memesDBObject {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String imageId;

    @ColumnInfo(name = "fullPath")
    public String fullPath;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name="type")
    public String type;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public memesDBObject(String imageId, String fullPath, String title, String type) {
        this.imageId = imageId;
        this.fullPath = fullPath;
        this.title = title;
        this.type = type;
    }
}