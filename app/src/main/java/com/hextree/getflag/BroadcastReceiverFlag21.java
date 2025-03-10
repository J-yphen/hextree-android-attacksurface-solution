package com.hextree.getflag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BroadcastReceiverFlag21 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(false);
        int resultCode = getResultCode();
        Log.i("Flag18Activity.BroadcastReceiver", "resultData " + resultData);
        Log.i("Flag18Activity.BroadcastReceiver", "resultExtras " + resultExtras);
        Log.i("Flag18Activity.BroadcastReceiver", "resultCode " + resultCode);

//        intent.putExtra("give-flag", true);
//        context.sendBroadcast(intent);
    }
}
