package com.hextree.getflag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BroadcastReceiverFlag18 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Utils.showDialog(context, intent);
        setResult(1,"Anything", new Bundle());
    }
}
