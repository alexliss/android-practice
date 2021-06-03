package com.redspot.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SelectCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        Button backToMain = findViewById(R.id.editCityDone);
        EditText city = findViewById(R.id.editTextCity);
        CheckBox windAndPressure = findViewById(R.id.showWindAndPressure);

        backToMain.setOnClickListener(v -> {
            Intent intent = new Intent(SelectCityActivity.this, MainActivity.class);
            intent.putExtra("city", city.getText().toString());
            intent.putExtra("wind", windAndPressure.isChecked());
            startActivity(intent);
        });
    }
}