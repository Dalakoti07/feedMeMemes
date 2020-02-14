package com.example.feedmememes.ActivitiesAndFragments.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class imageObject {
    @SerializedName("original")
    @Expose
    private imageDetails original;

    public imageDetails getOriginal() {
        return original;
    }

    public void setOriginal(imageDetails original) {
        this.original = original;
    }

}
