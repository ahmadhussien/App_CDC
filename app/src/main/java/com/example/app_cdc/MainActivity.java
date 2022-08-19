package com.example.app_cdc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickv(View v) {
        // Do something in response to button click
        Intent i = new Intent(MainActivity.this, Display.class);
        startActivity(i);
    }


    public void onClickc(View v) {
        // Do something in response to button click
        Intent i = new Intent(MainActivity.this, calculates.class);
        startActivity(i);
    }
    public void onClickReg(View v) {
        // Do something in response to button click
        Intent i = new Intent(MainActivity.this, RegPatients.class);
        startActivity(i);
    }

}