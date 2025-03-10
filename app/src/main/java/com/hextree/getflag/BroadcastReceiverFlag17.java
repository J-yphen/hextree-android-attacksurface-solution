package com.hextree.getflag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BroadcastReceiverFlag17 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Utils.showDialog(context, intent);
//        Intent intent = new Intent();
//        intent.putExtra("flag", "give-flag-17");
//        intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.receivers.Flag17Receiver");
//        BroadcastReceiver resultReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String resultData = getResultData();
//                Bundle resultExtra = getResultExtras(false);
//                int resultCode = getResultCode();
//                Log.i("Flag", Utils.dumpIntent(Hextree.this, intent));
//            }
//        };
//        sendOrderedBroadcast(intent, null, resultReceiver, null, RESULT_CANCELED, null, null);
    }
}
