package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Activity4 extends AppCompatActivity {
    private ImageButton button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        button1 = (ImageButton) findViewById(R.id.homebtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();

            }
        });
    }
    public void openActivity1(){
        Intent intent =new Intent(this, com.example.project.MainActivity.class);
        startActivity(intent);
}

}