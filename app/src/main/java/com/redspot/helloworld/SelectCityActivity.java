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
        CheckBox windAndPressure = findViewById(R.id.showWindAndPressure);
        EditText city = findViewById(R.id.editTextCity);

        backToMain.setOnClickListener(v -> {
            Intent intentResult = new Intent(SelectCityActivity.this, MainActivity.class);
            intentResult.putExtra(MainActivity.KEY_CITY, city.getText().toString());
            intentResult.putExtra(MainActivity.KEY_WIND, windAndPressure.isChecked());
            setResult(RESULT_OK, intentResult);
            finish();
        });
    }
}