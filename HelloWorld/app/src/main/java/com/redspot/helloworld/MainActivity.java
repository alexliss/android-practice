package com.redspot.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button refresh = findViewById(R.id.refresh);
        Button changeCity = findViewById(R.id.changeCity);
        //Button returnOnMain = findViewById(R.id.editCityDone);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "meow", Toast.LENGTH_SHORT).show();
            }
        });


        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.change_city);
            }
        });


//        returnOnMain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.activity_main);
//            }
//        });

        final TextView temperature = (TextView) findViewById(R.id.temperature);
        String tempValue = getResources().getString(R.string.temperature);
        tempValue += "! ;з";
        temperature.setText(tempValue);

    }


}