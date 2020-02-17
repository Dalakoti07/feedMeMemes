package com.example.feedmememes.ActivitiesAndFragments.network;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystems;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class downloadService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public downloadService(String name) {
        super(name);
    }
    public static final int UPDATE_PROGRESS = 8344;

    public downloadService() {
        super("DownloadService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        String urlToDownload = intent.getStringExtra("url");
        String filename=intent.getStringExtra("fileName");
        ResultReceiver receiver =  intent.getParcelableExtra("receiver");
        int position =intent.getIntExtra("position",0);
        Log.d("commonLogs"," position from intent in downloader is "+position);
        try {

            //create url and connect
            URL url = new URL(urlToDownload);
            URLConnection connection = url.openConnection();
            connection.connect();

            // this will be useful so that you can show a typical 0-100% progress bar
            int fileLength = connection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());
            Log.d("commonLogs", Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)+" is the dir from getExternalFilesDirs");
            String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)+"/"+filename ;
            OutputStream output = new FileOutputStream(path);

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;

                // publishing the progress....
                Bundle resultData = new Bundle();
                resultData.putInt("progress" ,(int) (total * 100 / fileLength));
                resultData.putInt("position",position);
                receiver.send(UPDATE_PROGRESS, resultData);
                output.write(data, 0, count);
            }

            // close streams
            output.flush();
            output.close();
            input.close();
            Log.d("commonLogs"," file is saved in "+Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)+"/"+filename );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bundle resultData = new Bundle();
        resultData.putInt("progress" ,100);

        receiver.send(UPDATE_PROGRESS, resultData);
    }
    }

