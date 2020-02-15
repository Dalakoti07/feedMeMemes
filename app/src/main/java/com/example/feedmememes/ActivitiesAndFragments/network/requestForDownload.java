package com.example.feedmememes.ActivitiesAndFragments.network;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

public class requestForDownload {
//    credits to https://stackoverflow.com/questions/3028306/download-a-file-with-android-and-showing-the-progress-in-a-progressdialog

    public requestForDownload(){

    }
    public static DownloadManager.Request getRequest(String url,String title,String Namehash){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("downloading memes");
        request.setTitle(title);

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Namehash+".gif")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        return request;
    }
}
