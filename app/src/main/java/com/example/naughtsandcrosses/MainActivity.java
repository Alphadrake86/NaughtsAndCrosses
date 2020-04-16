package com.example.naughtsandcrosses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonbc;
    private Button buttonbl;
    private Button buttonbr;

    private Button buttonmc;
    private Button buttonml;
    private Button buttonmr;

    private Button buttontc;
    private Button buttontl;
    private Button buttontr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
