package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
    private ImageButton button1;
    private ImageButton button2;
    private Button button3,button4,button5;
    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (ImageButton) findViewById(R.id.personbtn);
        button2 = (ImageButton) findViewById(R.id.cartbtn);
        button3 = (Button) findViewById(R.id.view1);
        button4 = (Button) findViewById(R.id.view2);
        button5 = (Button) findViewById(R.id.view3);



        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {
            showStartDialog();
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity8();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity5();

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity6();

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity7();


            }
        });



    }
        public void openActivity8(){
            Intent intent =new Intent(this, com.example.project.Activity8.class);
            startActivity(intent);
        }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeChangeReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(airplaneModeChangeReceiver);
    }


    public void openActivity3(){
        Intent intent =new Intent(this, com.example.project.Activity3.class);
        startActivity(intent);
    }
    public void openActivity5(){
        Intent intent =new Intent(this, com.example.project.Activity5.class);
        startActivity(intent);
    }
    private void showStartDialog() {
        new AlertDialog.Builder(this)
                .setTitle("graditute")
                .setMessage("welcome to DEV Store")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
    public void openActivity6(){
        Intent intent =new Intent(this, com.example.project.Activity6.class);
        startActivity(intent);
    }
    public void openActivity7(){
        Intent intent =new Intent(this, com.example.project.Activity7.class);
        startActivity(intent);
    }



}
