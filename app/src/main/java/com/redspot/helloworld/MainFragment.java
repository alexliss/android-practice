package com.redspot.helloworld;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.redspot.helloworld.weathermodel.WeatherRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class MainFragment extends Fragment {

    private final static int REQUEST_CODE_SELECT_CITY = 1;
    public final static String KEY_CITY = "city";
    public final static String KEY_WIND = "wind";
    public final static String WEATHER_URL_BASE = "https://api.openweathermap.org/data/2.5/weather";
    public static final String WEATHER_API_CALL = "&appid=";
    public final static String WEATHER_API_KEY = "";
    public final static String WEATHER_UNITS = "&units=metric";
    public final static String WEATHER_CITY = "?q=";
    public final static String WEATHER_LANG = "&lang=RU";

    private TextView temp;
    private TextView city;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button refresh = Objects.requireNonNull(getView()).findViewById(R.id.refresh);
        final Button changeCity = getView().findViewById(R.id.changeCity);
        final Button goToSettings = getView().findViewById(R.id.goToSettings);
        final Button goToBrowser = getView().findViewById(R.id.goToBrowser);
        final TextView counter = getView().findViewById(R.id.counter);
        final MainPresenter presenter = MainPresenter.getInstance();
        temp = getView().findViewById(R.id.temperature);
        city = getView().findViewById(R.id.city);

        counter.setText(((Integer)presenter.getCounter()).toString());
        initFutureWeather();

//        String instanceState;
//        if (savedInstanceState == null){
//            instanceState = getString(R.string.first_launch);
//        }
//        else {
//            instanceState = getString(R.string.relaunching);
//        }
//
//        Toast.makeText(getActivity(), instanceState + " - onCreate()", Toast.LENGTH_SHORT).show();

        refresh.setOnClickListener(weatherUpdate);

        changeCity.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), SelectCityActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT_CITY);
        });

        goToSettings.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
        });

        goToBrowser.setOnClickListener(view -> {
            final String url = getResources().getString(R.string.site);
            final Uri uri = Uri.parse(url);
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SELECT_CITY:
                // проверка на результат внутри для обработки случаев, когда результат не ок (специфический код, так сказатб)
                if (resultCode == Activity.RESULT_OK) {
                    TextView city = Objects.requireNonNull(getView()).findViewById(R.id.city);
                    String newCity = data.getStringExtra(KEY_CITY);
                    if (newCity != null && !newCity.equals("")) {
                        city.setText(newCity);
                    }
                    TextView windAndPressure = getView().findViewById(R.id.windAndPressure);
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

    public void initFutureWeather() {
        RecyclerView recyclerView = getActivity().findViewById(R.id.futureWeather);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration separator = new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL);
        separator.setDrawable(getActivity().getDrawable(R.drawable.future_weather_separator));
        recyclerView.addItemDecoration(separator);

        FutureWeatherAdapter adapter = new FutureWeatherAdapter(getResources());
        recyclerView.setAdapter(adapter);

    }

    View.OnClickListener weatherUpdate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                final URL uri = new URL(
                        WEATHER_URL_BASE + WEATHER_CITY + city.getText().toString() + WEATHER_API_CALL
                                + WEATHER_API_KEY + WEATHER_UNITS + WEATHER_LANG
                );
                final Handler handler = new Handler(); // Запоминаем основной поток
                new Thread(new Runnable() {
                    @SuppressLint("ShowToast")
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void run() {
                        HttpsURLConnection urlConnection = null;
                        try {
                            urlConnection = (HttpsURLConnection) uri.openConnection();
                            urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                            urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд

                            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                            String result = getLines(in);
                            // преобразование данных запроса в модель
                            Gson gson = new Gson();
                            final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                            // Возвращаемся к основному потоку
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    displayWeather(weatherRequest);
                                }
                            });
                        } catch (Exception e) {
                            Log.e(TAG, "Fail connection", e);
                            e.printStackTrace();
                            Snackbar.make(view, "Наверное, такого города нет. Либо беда с сетью", Snackbar.LENGTH_LONG)
                                    .setAction("Сменить город", view1 -> {
                                        Intent intent = new Intent(getContext(), SelectCityActivity.class);
                                        startActivityForResult(intent, REQUEST_CODE_SELECT_CITY);
                                    }).show();
                        } finally {
                            if (null != urlConnection) {
                                urlConnection.disconnect();
                            }
                        }
                    }
                }).start();
            } catch (MalformedURLException e) {
                Log.e(TAG, "Fail URI", e);
                e.printStackTrace();
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private String getLines(BufferedReader in) {
            return in.lines().collect(Collectors.joining("\n"));
        }

        @SuppressLint("DefaultLocale")
        private void displayWeather(WeatherRequest weatherRequest) {
            city.setText(weatherRequest.getName());
            temp.setText(String.format("%.1f", weatherRequest.getMain().getTemp())  + "℃");
        }
    };
}