package com.redspot.helloworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_SELECT_CITY = 1;
    public final static String KEY_CITY = "city";
    public final static String KEY_WIND = "wind";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button refresh = findViewById(R.id.refresh);
        final Button changeCity = findViewById(R.id.changeCity);
        final Button goToSettings = findViewById(R.id.goToSettings);
        final Button goToBrowser = findViewById(R.id.goToBrowser);
        final TextView counter = findViewById(R.id.counter);
        final MainPresenter presenter = MainPresenter.getInstance();
        final TextView city = findViewById(R.id.city);

        counter.setText(((Integer)presenter.getCounter()).toString());

        String instanceState;
        if (savedInstanceState == null){
            instanceState = getString(R.string.first_launch);
        }
        else {
            instanceState = getString(R.string.relaunching);
        }

        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();

        refresh.setOnClickListener(v -> {
            presenter.incrementCounter();
            counter.setText(((Integer)presenter.getCounter()).toString());
        });

        changeCity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_CITY);
        });

        goToSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            intent.putExtra("city", city.getText().toString());
            startActivity(intent);
        });

        goToBrowser.setOnClickListener(v -> {
            final String url = getResources().getString(R.string.site);
            final Uri uri = Uri.parse(url);
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        Log.d(null, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        Log.d(null, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d(null, "onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        Log.d(null, "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        Log.d(null, "onResume()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(null, "onRestoreInstanceState()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(null, "onRestart");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d(null, "onSaveInstanceState()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SELECT_CITY:
                // проверка на результат внутри для обработки случаев, когда результат не ок (специфический код, так сказатб)
                if (resultCode == RESULT_OK) {
                    TextView city = findViewById(R.id.city);
                    city.setText(data.getStringExtra(KEY_CITY));
                    TextView windAndPressure = findViewById(R.id.windAndPressure);
                    if (data.getBooleanExtra(KEY_WIND, false)) {
                        windAndPressure.setVisibility(View.VISIBLE);
                    } else {
                        windAndPressure.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}