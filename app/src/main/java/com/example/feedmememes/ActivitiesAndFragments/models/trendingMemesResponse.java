package com.example.feedmememes.ActivitiesAndFragments.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class trendingMemesResponse {
    @SerializedName("data")
    @Expose
    private List<memesData> data = null;

    public List<memesData> getData() {
        return data;
    }

    public void setData(List<memesData> data) {
        this.data = data;
    }

    public class memesData{
        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("images")
        @Expose
        private imageObject images;

        public imageObject getImages() {
            return images;
        }

        public void setImages(imageObject images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}