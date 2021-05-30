package com.redspot.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView greeting = (TextView) findViewById(R.id.temperature);
        String hello = getResources().getString(R.string.temperature);
        hello += "! ;з";
        greeting.setText(hello);
    }
}