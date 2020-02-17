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

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        super.onReceiveResult(resultCode, resultData);

        if (resultCode == downloadService.UPDATE_PROGRESS) {

            int progress = resultData.getInt("progress"); //get the progress
//            dialog.setProgress(progress);
//
//            if (progress == 100) {
//                dialog.dismiss();
//            }
            Log.d("commonLogs","current downloaded size is "+progress);
        }
    }
}
