package com.redspot.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        Button backToMain = findViewById(R.id.editCityDone);
        backToMain.setOnClickListener(v -> {
            Intent intent = new Intent(SelectCityActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}