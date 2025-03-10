package com.hextree.getflag;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ImplicitIntent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
//        Utils.showDialog(this, intent);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("token", 1094795585);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
