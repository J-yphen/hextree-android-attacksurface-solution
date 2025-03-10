package com.hextree.getflag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastReceiverFlag20 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Utils.showDialog(context, intent);
        intent.putExtra("give-flag", true);
        context.sendBroadcast(intent);
    }
}
