package com.redspot.helloworld;

import android.content.Intent;
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
        Button goToSettings = findViewById(R.id.goToSettings);

        refresh.setOnClickListener(v -> Toast.makeText(MainActivity.this, "meow", Toast.LENGTH_SHORT).show());

        changeCity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
            startActivity(intent);
        });

        goToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }
}