package com.redspot.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button refresh;
    Button changeCity;
    Button returnOnMain;
    Button goToSettings;
    Button returnFromSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startMainScreen();

    }

    public void startMainScreen() {
        setContentView(R.layout.activity_main);
        refresh = findViewById(R.id.refresh);
        changeCity = findViewById(R.id.changeCity);
        goToSettings = findViewById(R.id.goToSettings);
        refreshOnClick();
        changeCityOnClick();
        goToSettingsOnClick();
    }

    public void startChangeCityScreen() {
        setContentView(R.layout.change_city);
        returnOnMain = findViewById(R.id.editCityDone);
        returnOnMainOnClick();
    }

    public void startSettingsScreen() {
        setContentView(R.layout.settings);
        returnFromSettings = findViewById(R.id.backToMain);
        returnFromSettingsOnClick();
    }

    public void refreshOnClick() {
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "meow", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeCityOnClick() {
        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChangeCityScreen();
            }
        });
    }

    public void returnOnMainOnClick() {
        returnOnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainScreen();
            }
        });
    }

    public void goToSettingsOnClick() {
        goToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettingsScreen();
            }
        });
    }

    public void returnFromSettingsOnClick() {
        returnFromSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainScreen();
            }
        });
    }
}