package com.example.project;

import android.app.LoaderManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;


import com.example.project.Database.OrderContract;

import java.nio.channels.Channel;


public class Activity3 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private ImageButton button1;
    public CartAdapter mAdapter;
    Button shownoti;
    private static final String CHANNEl_ID="channel_id01";
    private static final  int NOTIFICATION_ID= 1;

    public static final int LOADER = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Button clearthedata = findViewById(R.id.clearthedatabase);
        button1 = (ImageButton) findViewById(R.id.arrowcart);
        shownoti=findViewById(R.id.submitorder);


        shownoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
                int deletethedata = getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI, null, null);
            }
        });



        clearthedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletethedata = getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI, null, null);
            }
        });

        getLoaderManager().initLoader(LOADER, null, this);

        ListView listView = findViewById(R.id.list);
        mAdapter = new CartAdapter(this, null);
        listView.setAdapter(mAdapter);





        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();

            }
        });
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY,

        };

        return new CursorLoader(this, OrderContract.OrderEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mAdapter.swapCursor(null);
    }





 private void showNotification(){
        createNotificationChannel();
NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEl_ID);
builder.setSmallIcon(R.mipmap.ic_launcher);
builder.setContentTitle("Dev Store");
builder.setContentText("your order has been submitted successfully");
builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

     NotificationManagerCompat notificationManagerCompat =NotificationManagerCompat.from(this);
     notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

 }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name ="my";
            String description="mydhdhj";
            int importance =NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEl_ID,name,importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }


    public void openActivity1(){
        Intent intent =new Intent(this, com.example.project.MainActivity.class);
        startActivity(intent);
    }
}