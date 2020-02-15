package com.example.feedmememes.ActivitiesAndFragments.network;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class requestForDownload {
//    credits to https://stackoverflow.com/questions/3028306/download-a-file-with-android-and-showing-the-progress-in-a-progressdialog
    Context context;
    public requestForDownload(Context context){
        this.context=context;
    }
    public static DownloadManager.Request getRequest(String url,String title,String Namehash,Context context){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("downloading memes");
        request.setTitle(title);
//      check if a file is already present or not
        File file=new File("/Phone/Downloads/"+Namehash+".gif");
        Log.d("commonTag"," download request for "+Environment.DIRECTORY_DOWNLOADS+" and hash is "+Namehash+".gif and file path is "+file.getAbsolutePath());
        boolean isPresent=false;
        if(file.exists()){
            isPresent=true;
            Log.d("commonTag"," file is present ");
        }else{
            Log.d("commonTag","file not present at "+file.getAbsolutePath());
        }
        if(!isPresent){
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Namehash+".gif");
            Log.d("commonTag"," thus downloading to ");
            return request;
        }
//                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        return null;
    }
}
