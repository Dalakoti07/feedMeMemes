package com.example.feedmememes.ActivitiesAndFragments.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

public class downloadResultReceiver extends ResultReceiver {

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public downloadResultReceiver(Handler handler) {
        super(handler);
    }

    public interface callDB{
        void doSomeTaskInDB(int position);
    }
    private callDB listenerToCompletion;
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        super.onReceiveResult(resultCode, resultData);

        if (resultCode == downloadService.UPDATE_PROGRESS) {
            int position =resultData.getInt("position");
            int progress = resultData.getInt("progress"); //get the progress
//            dialog.setProgress(progress);
//
            if (progress == 100) {
//                dialog.dismiss();
                listenerToCompletion.doSomeTaskInDB(position);
            }
            Log.d("commonLogs","current downloaded size is "+progress);
        }
    }

    public void addListener(callDB listener){
        this.listenerToCompletion=listener;
    }
}
