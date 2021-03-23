package com.test.reviewnotificationsandalerts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createChannel();
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
    }
    private void sendNotification(){
        Intent intent=new Intent(this,this.getClass());
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder myNotification=new NotificationCompat.Builder(this,"1");
        myNotification.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("this is a notification for you ")
                .setContentTitle("HI")
                .setContentIntent(pi);
        NotificationManagerCompat.from(this).notify(10,myNotification.build());
    }
    private void createChannel(){
       if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
           NotificationChannel channel=new NotificationChannel("1","myChannel",NotificationManager.IMPORTANCE_DEFAULT);
           channel.setDescription("this my first channel for this app");
           NotificationManager notificationManager=getSystemService(NotificationManager.class);
           notificationManager.createNotificationChannel(channel);
       }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                Toast.makeText(this, "this is a long message", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn2:
                Toast.makeText(this, "this is a short message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                final Toast msg=Toast.makeText(this, "this is a fixed message length",Toast.LENGTH_LONG);
                msg.show();
                Handler hd=new Handler();
                hd.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        msg.cancel();
                    }
                },Integer.parseInt(((EditText)findViewById(R.id.durree)).getText().toString())*1000);

                break;
            case R.id.btn4:
                sendNotification();
                break;
            case R.id.btn5:
                AlertDialog.Builder alert=new AlertDialog.Builder(this);
                alert.setTitle("alert")
                        .setMessage("this is a simple message with button OK")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "You clicked ok Button", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher_foreground);
                alert.create().show();
                break;
            case R.id.btn6:
                new AlertDialog.Builder(this).setTitle("alert")
                        .setMessage("this is a message with buttons YES/NO")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "You clicked Yes button", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "You clicked NO button", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(R.drawable.ic_launcher_foreground).create().show();
                break;
            case R.id.btn7:
                new AlertDialog.Builder(this).setTitle("alert")
                        .setMessage("this is a message with YES/NO/CANCEL buttons")
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setCancelable(true)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "You clicked Yes button", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "You clicked NO button", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "You clicked CANCEL button", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
                break;
        }
    }
}