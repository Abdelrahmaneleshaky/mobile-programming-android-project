package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.res.Resources;

import java.util.Locale;


public class Activity8 extends AppCompatActivity {
    private Button button;
    private Button buttonen;
    private Button buttonfr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);
        button =  findViewById(R.id.signup);
        buttonen= findViewById(R.id.lang1);
        buttonfr= findViewById(R.id.lang2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();

            }
        });
        buttonen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changelang(Activity8.this,"en");
                finish();
                startActivity(getIntent());

            }
        });
        buttonfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changelang(Activity8.this,"fr");
                finish();
                startActivity(getIntent());
            }
        });
    }
    public void openActivity4() {
        Intent intent =new Intent(this, com.example.project.Activity4.class);
        startActivity(intent);
    }
     public void changelang(Context context, String langCode){
     Locale locale = new Locale(langCode);
     locale.setDefault(locale);
         Resources resources=context.getResources();
         Configuration config =resources.getConfiguration();
         config.setLocale(locale);
         resources.updateConfiguration(config, resources.getDisplayMetrics());

     }

}