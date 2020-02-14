package com.example.feedmememes.ActivitiesAndFragments.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class networkerProvider {
    private static Retrofit retrofitInstance =null;
    private static String API_BASE_URL = "https://api.giphy.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );


    public static Retrofit getInstance(){
        if(retrofitInstance==null){
            Retrofit retrofit = builder.client(
                    httpClient.build()
            )
                    .build();
            retrofitInstance=retrofit;
            return retrofitInstance;
        }else{
            return retrofitInstance;
        }
    }
}
