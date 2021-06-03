package com.redspot.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button backToMain = findViewById(R.id.backToMain);
        Switch toDarkTheme = findViewById(R.id.switchTheme);

        final SettingsPresenter presenter = SettingsPresenter.getInstance();
        toDarkTheme.setChecked(presenter.isThemeDark());

        toDarkTheme.setOnClickListener(v -> presenter.setDarkThemeSwitch(toDarkTheme.isChecked()));

        backToMain.setOnClickListener(v -> finish());
    }
}